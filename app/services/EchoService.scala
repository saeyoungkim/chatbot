package services

import javax.inject.Inject
import models.api.input.WebhookEvent
import play.api.libs.json.{JsNumber, JsObject, Json}
import play.api.mvc.Request
import repositories.ws.ReplyRepositoryWS
import utils.LineVerifier

import scala.concurrent.{ExecutionContext, Future}
import scala.util.control.NonFatal
import scala.util.{Failure, Success}

class EchoService @Inject()(
  lineVerifier: LineVerifier,
  replyRepositoryWS: ReplyRepositoryWS
)(
  implicit val ec: ExecutionContext
){
  def echo(req: Request[WebhookEvent]): Future[Unit] = {
    if(lineVerifier.validateLineSignature(req)) {
      println("Line-X-Signature verified!!")
      println(req.body.toString)

      val process = req.body.events.map { event =>
        replyRepositoryWS.sendEchoTextReply(event.message.get.text.get, event.replyToken.get)
      }

      val result = Future.sequence(process)

      result
        .map(_ => ():Unit)
        .recoverWith {
        case NonFatal(ex) =>
          println(ex)
          throw new RuntimeException(s"cannot post data", ex)
      }
    }
    else
      Future.successful(Json.obj("status"->JsNumber(200), "message"->"unaccepted"))
  }
}

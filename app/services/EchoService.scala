package services

import javax.inject.Inject
import javax.xml.bind.ValidationException
import models.api.input.WebhookEvent
import play.api.libs.json.{JsError, JsNumber, JsSuccess, Json}
import play.api.mvc.{AnyContent, Request}
import repositories.ws.ReplyRepositoryWS
import utils.LineVerifier

import scala.concurrent.{ExecutionContext, Future}
import scala.util.control.NonFatal

class EchoService @Inject()(
  lineVerifier: LineVerifier,
  replyRepositoryWS: ReplyRepositoryWS
)(
  implicit val ec: ExecutionContext
){
  def echo()(implicit req: Request[AnyContent]): Future[Unit] = {
    if(lineVerifier.validateSignature(req)) {

      val process = req.body.asJson.map {
        _.validate[WebhookEvent] match {
          case JsSuccess(webhookEvent, _) => webhookEvent.events.map { event =>
            replyRepositoryWS.sendEchoTextReply(event.message.get.text.get, event.replyToken.get)
          }
          case JsError(_) => throw new ValidationException("invalid body as WebhookEvent json type")
        }
      } getOrElse {
        throw new IllegalArgumentException("invalid request body as json")
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

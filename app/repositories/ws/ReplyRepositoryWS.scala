package repositories.ws

import javax.inject.Inject
import models.api.output.ReplyMessage
import models.api.output.message.{Message, MessageType}
import play.api.Configuration
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import utils.ConfPath
import utils.props.consts.HttpHeader._

import scala.concurrent.ExecutionContext


class ReplyRepositoryWS @Inject()(
  ws: WSClient,
  conf: Configuration
)(
  implicit val ec: ExecutionContext
) {
  final private val AccessToken = conf.get[String](ConfPath.Line.AccessToken)
  final private val ReplyUrl = conf.get[String](ConfPath.Line.ReplyUrlPath)

  def sendEchoTextReply(msg: String, replyToken: String) = {
    val jsonReply = Json.toJson(ReplyMessage(replyToken, Seq(Message(MessageType.Message, msg)), Some(false)))

    println(jsonReply)

    ws
      .url(ReplyUrl)
      .withHttpHeaders(
        CONTENT_TYPE -> APPLICATION_JSON,
        AUTHORIZATION -> lineAuthorization(AccessToken)
      )
      .post(jsonReply)
      .map(res => println(res.body))
  }
}

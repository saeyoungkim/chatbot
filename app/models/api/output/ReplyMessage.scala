package models.api.output

import models.api.output.message.Message
import play.api.libs.json.Json

case class ReplyMessage(
  replyToken: String,
  messages: Seq[Message],
  notificationDisabled: Option[Boolean]
) {
  def isValidMessagesSize: Boolean = messages.size > 5
}

object ReplyMessage {
  implicit val replyMessageWrites = Json.writes[ReplyMessage]
}

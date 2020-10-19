package models.api.output.message

import play.api.libs.json.Json

case class Message(
  messageType: MessageType,
  text: String
)

object Message {
  implicit val messageWrites = Json.writes[Message]
}

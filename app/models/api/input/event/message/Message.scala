package models.api.input.event.message

import models.api.input.event.message.emoji.Emoji

import play.api.libs.json.__
import play.api.libs.functional.syntax._

case class Message(
  id: String,
  messageType: MessageType,
  text: Option[String],
  emojis: Seq[Emoji]
)

object Message {
  implicit val messageReads = (
    (__ \ "id").read[String] and
      (__ \ "type").read[MessageType] and
      (__ \ "text").readNullable[String] and
      (__ \ "emojis").read[Seq[Emoji]]
  )(apply _)
}

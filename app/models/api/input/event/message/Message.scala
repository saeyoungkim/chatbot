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
  implicit val messageFormats = (
    (__ \ "id").format[String] and
      (__ \ "type").format[MessageType] and
      (__ \ "text").formatNullable[String] and
      (__ \ "emojis").format[Seq[Emoji]]
  )(apply _, unlift(unapply _))
}

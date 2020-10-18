package models.api.input.event.message

import models.api.input.event.message.emoji.Emoji
import play.api.libs.json.{Reads, Writes, __}
import play.api.libs.functional.syntax._

case class Message(
  id: String,
  messageType: MessageType,
  text: Option[String],
  emojis: Option[Seq[Emoji]]
)

object Message {
  implicit val messageFormats = (
    (__ \ "id").format[String] and
      (__ \ "type").format[MessageType] and
      (__ \ "text").formatNullable[String] and
      (__ \ "emojis").formatNullable[Seq[Emoji]]
  )(apply _, unlift(unapply _))
}

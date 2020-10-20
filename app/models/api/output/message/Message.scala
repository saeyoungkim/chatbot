package models.api.output.message

import play.api.libs.json.__
import play.api.libs.functional.syntax._

case class Message(
  messageType: MessageType,
  text: String
)

object Message {
  implicit val messageWrites = (
    (__ \ "type").write[MessageType] and
      (__ \ "text").write[String]
  )(unlift(unapply _))
}

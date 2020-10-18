package models.api.input.event

import models.api.input.event.message.Message
import models.api.input.event.mode.Mode
import models.api.input.event.source.Source

import play.api.libs.json.__
import play.api.libs.functional.syntax._

case class Event(
                  replyToken: Option[String],
                  eventType: EventType,
                  mode: Mode,
                  timeStamp: Long,
                  source: Source,
                  message: Option[Message]
)

object Event {
  implicit val webhookEventFormats = (
    (__ \ "replyToken").formatNullable[String] and
      (__ \ "type").format[EventType] and
      (__ \ "mode").format[Mode] and
      (__ \ "timestamp").format[Long] and
      (__ \ "source").format[Source] and
      (__ \ "message").formatNullable[Message]
  )(apply _, unlift(unapply _))
}

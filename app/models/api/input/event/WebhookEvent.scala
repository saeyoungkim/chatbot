package models.api.input.event

import models.api.input.event.message.Message
import models.api.input.event.mode.Mode
import models.api.input.event.source.Source

import play.api.libs.json.__
import play.api.libs.functional.syntax._

case class WebhookEvent(
  replyToken: Option[String],
  eventType: WebhookEventType,
  mode: Mode,
  timeStamp: Long,
  source: Source,
  message: Option[Message]
)

object WebhookEvent {
  implicit val webhookEventReads = (
    (__ \ "replyToken").readNullable[String] and
      (__ \ "type").read[WebhookEventType] and
      (__ \ "mode").read[Mode] and
      (__ \ "timestamp").read[Long] and
      (__ \ "source").read[Source] and
      (__ \ "type").readNullable[Message]
  )(apply _)
}

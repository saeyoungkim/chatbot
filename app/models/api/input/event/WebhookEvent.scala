package models.api.input.event

import models.api.input.event.message.Message
import models.api.input.event.mode.Mode
import models.api.input.event.source.Source

case class WebhookEvent(
  replyToken: Option[String],
  eventType: WebhookEventType,
  mode: Mode,
  timeStamp: Long,
  source: Source,
  message: Message
)

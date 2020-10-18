package models.api.input

import play.api.libs.json.Json

case class WebhookEvent(
  destination: String,
  events: Seq[WebhookEvent]
)

object WebhookEvent {
  implicit val eventReads = Json.format[WebhookEvent]
}

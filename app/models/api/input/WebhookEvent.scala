package models.api.input

import models.api.input.event.Event
import play.api.libs.json.Json

case class WebhookEvent(
  destination: String,
  events: Seq[Event]
)

object WebhookEvent {
  implicit val eventReads = Json.format[WebhookEvent]
}

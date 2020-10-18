package models.api.input

import models.api.input.event.WebhookEvent
import play.api.libs.json.Json

case class Event(
  destination: String,
  events: Seq[WebhookEvent]
)

object Event {
  implicit val eventReads = Json.reads[Event]
}

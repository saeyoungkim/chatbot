package models.api.input

import models.api.input.event.WebhookEvent

case class Event(
  destination: String,
  events: Seq[WebhookEvent]
)

package models.api.input.event

import utils.props.enums.{Enum, EnumsReadableString}

sealed abstract class WebhookEventType(val code: String) extends Enum[String]

object WebhookEventType extends EnumsReadableString[WebhookEventType] {
  case object Message extends WebhookEventType(code = "message")
  case object Follow extends WebhookEventType(code = "follow")

  override protected val values: Seq[WebhookEventType] = Seq(
    Message,
    Follow
  )
}

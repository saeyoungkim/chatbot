package models.api.input.event

import utils.props.enums.{Enum, EnumsFormattableString}

sealed abstract class WebhookEventType(val code: String) extends Enum[String]

object WebhookEventType extends EnumsFormattableString[WebhookEventType] {
  case object Message extends WebhookEventType(code = "message")
  case object Follow extends WebhookEventType(code = "follow")

  override protected val values: Seq[WebhookEventType] = Seq(
    Message,
    Follow
  )
}

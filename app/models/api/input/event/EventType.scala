package models.api.input.event

import utils.props.enums.{Enum, EnumsFormattableString}

sealed abstract class EventType(val code: String) extends Enum[String]

object EventType extends EnumsFormattableString[EventType] {
  case object Message extends EventType("message")
  case object Follow extends EventType("follow")

  override protected val values: Seq[EventType] = Seq(
    Message,
    Follow
  )
}

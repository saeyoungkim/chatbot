package models.api.input.event.source

import utils.props.enums.{Enum, EnumsFormattableString}

sealed abstract class SourceType(val code: String) extends Enum[String]

object SourceType extends EnumsFormattableString[SourceType]{

  case object User extends SourceType("user")
  case object Room extends SourceType("room")
  case object Group extends SourceType("group")

  override protected val values: Seq[SourceType] = Seq(
    User,
    Room,
    Group
  )
}

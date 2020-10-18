package models.api.input.event.mode

import utils.props.enums.{Enum, EnumsReadableString}

sealed abstract class Mode(val code: String) extends Enum[String]

object Mode extends EnumsReadableString[Mode] {

  case object Active extends Mode("active")
  case object Stanby extends Mode("stanby")

  override protected val values: Seq[Mode] = Seq(
    Active,
    Stanby
  )
}

package models.api.output.message

import utils.props.enums.{Enum, EnumsWritableString}

sealed abstract class MessageType(val code: String) extends Enum[String]

object MessageType extends EnumsWritableString[MessageType] {

  case object Text extends MessageType("text")

  override protected val values: Seq[MessageType] = Seq(
    Text
  )
}

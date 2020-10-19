package models.api.output.message

import utils.props.enums.{Enum, EnumsReadableString}

sealed abstract class MessageType(val code: String) extends Enum[String]

object MessageType extends EnumsReadableString[MessageType] {

  case object Message extends MessageType("message")

  override protected val values: Seq[MessageType] = Seq(
    Message
  )
}

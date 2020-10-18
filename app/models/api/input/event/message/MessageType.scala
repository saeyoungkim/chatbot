package models.api.input.event.message

import utils.props.enums.{Enum, EnumsReadableString}

sealed abstract class MessageType(val code: String) extends Enum[String]

object MessageType extends EnumsReadableString[MessageType] {

  case object Text extends MessageType("text")
  case object Image extends MessageType("image")
  case object Video extends MessageType("video")
  case object Audio extends MessageType("audio")
  case object File extends MessageType("file")
  case object Location extends MessageType("location")
  case object Sticker extends MessageType("sticker")

  override protected val values: Seq[MessageType] = Seq(
    Text,
    Image,
    Video,
    Audio,
    File,
    Location,
    Sticker
  )
}

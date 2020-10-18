package models.api.input.event.message.emoji

import play.api.libs.json.Json

case class Emoji(
  index: Int,
  length: Int,
  productId: String,
  emojiId: String
)

object Emoji {
  implicit val emojiReads = Json.reads[Emoji]
}

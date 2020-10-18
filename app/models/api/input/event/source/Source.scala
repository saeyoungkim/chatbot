package models.api.input.event.source

import play.api.libs.json.__
import play.api.libs.functional.syntax._

case class Source(
  sourceType: SourceType,
  roomId: Option[String],
  groupId: Option[String],
  userId: String
)

object Source {
  implicit val reads = (
    (__ \ "type").read[SourceType] and
      (__ \ "roomId").readNullable[String] and
      (__ \ "groupId").readNullable[String] and
      (__ \ "userId").read[String]
  )(apply _)
}

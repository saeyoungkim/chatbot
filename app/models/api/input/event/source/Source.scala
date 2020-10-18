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
  implicit val sourceFormats = (
    (__ \ "type").format[SourceType] and
      (__ \ "roomId").formatNullable[String] and
      (__ \ "groupId").formatNullable[String] and
      (__ \ "userId").format[String]
  )(apply _, unlift(unapply _))
}

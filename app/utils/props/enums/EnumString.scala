package utils.props.enums

import play.api.libs.json.{JsString, JsSuccess, Reads, Writes}

trait EnumsWritableString[B <: Enum[String]] extends Enums[B, String] {
  implicit val enumsWrites = Writes[B] { w =>
    JsString(w.code)
  }
}

trait EnumsReadableString[B <: Enum[String]] extends Enums[B, String] {
  implicit val enumsReads = Reads[B] {
    _ match {
      case JsString(v) => getOpt(v).map(JsSuccess(_)).getOrElse(
        throw new IllegalArgumentException(s"${this.getClass.getName} does not contain $v"))
      case _ => throw new IllegalArgumentException(s"String type of json value must be needed.")
    }
  }
}

trait EnumsFormattableString[B <: Enum[String]]
  extends EnumsWritableString[B]
  with EnumsReadableString[B]

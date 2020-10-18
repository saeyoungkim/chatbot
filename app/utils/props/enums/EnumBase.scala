package utils.props.enums

import play.api.libs.json.{JsString, Reads, Writes}

/**
 * 全てのEnumはこのトレイとを継承する
 *
 * @tparam A
 */
trait Enum[+A] {
  val code: A

  override def toString: String = code.toString
}

trait Enums[B <: Enum[A], A] {
  protected val values: Seq[B]

  def getOpt(a: A): Option[B] = values.find(_.code == a)

  def getOrError(a: A): B = getOpt(a).getOrElse {
    throw new IllegalArgumentException(s"${this.getClass.getName} does not contain code $a")
  }
}

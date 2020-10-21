package utils.props.consts

object HttpHeader {
  final val CONTENT_TYPE: String = "Content-Type"
  final val APPLICATION_JSON: String = "application/json"
  final val AUTHORIZATION: String = "Authorization"
  def lineAuthorization(token: String): String = s"$token"
}

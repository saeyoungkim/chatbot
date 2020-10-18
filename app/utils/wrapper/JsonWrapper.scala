package utils.wrapper

import play.api.libs.json.Json

object JsonWrapper {
  implicit class ContentToJsonWrapper[A](val content: A) {
    def toJson = Json.toJson(content)
  }
}

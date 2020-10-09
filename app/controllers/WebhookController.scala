package controllers

import javax.inject.Inject
import play.api.libs.json.{JsNumber, Json}
import play.api.mvc.{AbstractController, ControllerComponents}

class WebhookController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def webhook() = Action {
    Ok(Json.obj("status"->JsNumber(OK), "message"->"accepted"))
  }
}

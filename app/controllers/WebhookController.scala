package controllers

import javax.inject.Inject
import play.api.libs.json.{JsNumber, Json}
import play.api.mvc.{AbstractController, ControllerComponents}
import utils.Logger

import scala.concurrent.Future

class WebhookController @Inject()(cc: ControllerComponents) extends AbstractController(cc)
  with Logger {
  def webhook() = Action.async { implicit req =>
    logger.info(req.body.toString)
    Future.successful(Ok(Json.obj("status"->JsNumber(OK), "message"->"accepted")))
  }
}

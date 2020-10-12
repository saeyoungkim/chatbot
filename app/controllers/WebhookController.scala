package controllers

import javax.inject.Inject
import play.api.libs.json.{JsNumber, Json}
import play.api.mvc.{AbstractController, ControllerComponents}
import utils.{LineVerifier, Logger}

import scala.concurrent.Future

class WebhookController @Inject()(
  cc: ControllerComponents,
  lineVerifier: LineVerifier
) extends AbstractController(cc)
  with Logger {
  def webhook() = Action.async { implicit req =>
    logger.info("message received")

    if(lineVerifier.validateSignature) Future.successful(Ok(Json.obj("status"->JsNumber(OK), "message"->"accepted")))
    else Future.successful(Ok(Json.obj("status"->JsNumber(OK), "message"->"unaccepted")))
  }
}

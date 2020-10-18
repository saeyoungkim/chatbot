package controllers

import javax.inject.Inject
import models.api.input.WebhookEvent
import play.api.Logger
import play.api.libs.json.{JsError, JsNumber, JsSuccess, Json}
import play.api.mvc.{AbstractController, ControllerComponents}
import utils.LineVerifier

import scala.concurrent.Future

class WebhookController @Inject()(
  cc: ControllerComponents,
  lineVerifier: LineVerifier
) extends AbstractController(cc) {
  private val logger = Logger(this.getClass)

  def webhook() = Action.async(parse.json[WebhookEvent]) { implicit req =>
    logger.info("message received")

    if(lineVerifier.validateLineSignature(req)) Future.successful(Ok(Json.obj("status"->JsNumber(OK), "message"->"accepted")))
    else Future.successful(BadRequest(Json.obj("status"->JsNumber(BAD_REQUEST), "message"->"unaccepted")))
  }
}

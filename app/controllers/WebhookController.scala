package controllers

import javax.inject.Inject
import models.api.input.WebhookEvent
import play.api.libs.json.{JsError, JsNumber, JsSuccess, Json}
import play.api.mvc.{AbstractController, ControllerComponents}
import utils.{LineVerifier, Logger}

import scala.concurrent.Future

class WebhookController @Inject()(
  cc: ControllerComponents,
  lineVerifier: LineVerifier
) extends AbstractController(cc)
  with Logger {
  def webhook() = Action.async{ implicit req =>
    logger.info("message received")
    println(req.body.asJson.map(Json.stringify(_)))
    req.body.asJson.map(_.validate[WebhookEvent] match {
      case JsSuccess(v, _) => println(v)
      case JsError(_) => println(_)
    })

    if(lineVerifier.validateSignature) Future.successful(Ok(Json.obj("status"->JsNumber(OK), "message"->"accepted")))
    else Future.successful(BadRequest(Json.obj("status"->JsNumber(BAD_REQUEST), "message"->"unaccepted")))
  }
}

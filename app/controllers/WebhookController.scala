package controllers

import javax.inject.Inject
import models.api.input.WebhookEvent
import play.api.Logger
import play.api.libs.json.{JsNumber, JsObject, JsString}
import play.api.mvc.{AbstractController, ControllerComponents}
import services.EchoService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.control.NonFatal

class WebhookController @Inject()(
  cc: ControllerComponents,
  echoService: EchoService
) extends AbstractController(cc) {
  private val logger = Logger(this.getClass)

  def webhook() = Action.async(parse.json[WebhookEvent]) { implicit req =>
    logger.info("message received")

    echoService.echo(req)
      .map { _ =>
        Ok(
          JsObject(Seq("status" -> JsNumber(OK)))
        )
      }
      .recover {
        case NonFatal(ex) => BadRequest(
          JsObject(
            Seq("status" -> JsNumber(BAD_REQUEST), "message" -> JsString(ex.getMessage))
          )
        )
      }
  }
}

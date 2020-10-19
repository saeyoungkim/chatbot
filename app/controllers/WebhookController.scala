package controllers

import javax.inject.Inject
import models.api.input.WebhookEvent
import play.api.Logger
import play.api.mvc.{AbstractController, ControllerComponents}
import services.EchoService

import scala.concurrent.ExecutionContext.Implicits.global

class WebhookController @Inject()(
  cc: ControllerComponents,
  echoService: EchoService
) extends AbstractController(cc) {
  private val logger = Logger(this.getClass)

  def webhook() = Action.async(parse.json[WebhookEvent]) { implicit req =>
    logger.info("message received")

    echoService.echo(req)
      .map(Ok(_))
      .recoverWith(BadRequest(_))
  }
}

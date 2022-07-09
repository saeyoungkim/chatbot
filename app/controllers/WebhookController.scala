package controllers

import javax.inject.Inject
import play.api.Logger
import play.api.mvc.{AbstractController, ControllerComponents}
import services.Dispatcher

class WebhookController @Inject()(
  cc: ControllerComponents,
  dispatcher: Dispatcher
) extends AbstractController(cc) {
  private val logger = Logger(this.getClass)

  def webhook() = Action.async { implicit req =>
    logger.info("message received")

    dispatcher.dispatch(req)
  }
}

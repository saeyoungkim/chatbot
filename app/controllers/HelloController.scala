package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import utils.Logger

class HelloController @Inject()(cc: ControllerComponents) extends AbstractController(cc)
  with Logger {
  def hello: Action[AnyContent] = Action { implicit req =>
    Ok("hi")
  }
}

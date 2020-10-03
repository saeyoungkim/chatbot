package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

class HelloController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def hello: Action[AnyContent] = Action { implicit req =>
    Ok("hi")
  }
}

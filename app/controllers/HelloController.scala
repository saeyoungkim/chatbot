package controllers

import javax.inject.Inject
import play.api.Logger
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

class HelloController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  private val logger = Logger(this.getClass)

  def hello: Action[AnyContent] = Action { implicit req =>
    throwException()
  }

  def throwException() = {
    throw new Exception("error")
  }
}

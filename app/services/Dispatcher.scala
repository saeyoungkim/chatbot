package services

import javax.inject.Inject
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.Future

class Dispatcher @Inject()(
  weatherService: WeatherService,
  echoService: EchoService
){
  def dispatch(req: Request[AnyContent]): Future[Unit] = {
    // TODO @DispatchableServices で宣言すると
    // TODO DispatchableServiceを引き継いているServiceが呼ばれるようにする
  }
}

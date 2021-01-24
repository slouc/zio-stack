package slouc.server

import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import slouc.zio.server.AppTaskThrow
import zio.interop.catz._

object Routes {

  def userRoutes(): HttpRoutes[AppTaskThrow] = {
    val dsl = new Http4sDsl[AppTaskThrow] {}
    import dsl._
    HttpRoutes.of[AppTaskThrow] {

      case GET -> Root / "healthy" => Ok()

      case GET -> Root / "ready"  => Ok()

    }
  }

}

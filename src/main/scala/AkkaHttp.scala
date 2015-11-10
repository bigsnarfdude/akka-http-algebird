
// akka
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.event.Logging

// scala
import scala.io.StdIn

// typesafe
import com.typesafe.config.ConfigFactory

object AkkaHttpApp extends Service {

  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem()
    implicit val mat = ActorMaterializer()
    import system.dispatcher
    
    val config = ConfigFactory.load()
    val logger = Logging(system, getClass)

    Http().bindAndHandle(routes, config.getString("http.interface"), config.getInt("http.port"))

    StdIn.readLine("Hit ENTER to exit")
    system.shutdown()
    system.awaitTermination()
  } 
}
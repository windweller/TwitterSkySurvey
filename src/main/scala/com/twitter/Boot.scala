package com.twitter


import akka.actor.{Props, ActorSystem}
import akka.io.{IO, Tcp}
import com.twitter.models.pgdb._
import spray.can.Http
import com.twitter.api._

object Boot extends App with MainActors with RootApi {

  //construct database tables; it needs improvement
  implicit lazy val system = ActorSystem("twitter-sky-survey")

  DAL.databaseInit()

  IO(Http) ! Http.Bind(rootService, interface = Config.host, port = Config.portHTTP)
}

object Config {
  import com.typesafe.config.ConfigFactory

  private val config = ConfigFactory.load
  config.checkValid(ConfigFactory.defaultReference)

  val host = config.getString("service.host")
  val portHTTP = config.getInt("service.port")
  val portTcp = config.getInt("service.ports.tcp")
  val portWs = config.getInt("service.ports.ws")

  //database for both deployment and development
  val extractPattern = """(\/\/)(.+?):(.+?)@""".r

  val tempConnect = config.getString("db.postgresql.connect")
  val dbConnect = if (!tempConnect.contains("postgresql"))
    "jdbc:"+tempConnect.replace("postgres", "postgresql").replace("172.17.42.1", "104.131.214.249")
  else tempConnect

  val dbURL = extractPattern.replaceFirstIn(dbConnect, "//")
  val dbUser = for (m <- extractPattern.findFirstMatchIn(dbConnect)) yield m.group(2)
  val dbPassword = for (m <- extractPattern.findFirstMatchIn(dbConnect)) yield m.group(3)
  val dbDriver = config.getString("db.postgresql.driver")

}
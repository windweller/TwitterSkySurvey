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

  //
  val dbURL = config.getString("db.postgresql.url")
  val dbUser = config.getString("db.postgresql.user")
  val dbPassword = config.getString("db.postgresql.password")
  val dbDriver = config.getString("db.postgresql.driver")

}
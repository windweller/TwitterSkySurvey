package com.twitter.models.pgdb

import akka.actor.{Props, ActorSystem}
import scala.util.{Failure, Success, Try}
import com.twitter.Config._
import scala.slick.driver.PostgresDriver.simple._
import scala.slick.jdbc.meta.MTable


object DAL {
  val db = (dbUser, dbPassword) match {
    case (None, None) => Database.forURL(dbConnect, driver=dbDriver)
    case _ => Database.forURL(url = dbURL, user=dbUser.get, password=dbPassword.get, driver = dbDriver)
  }

  def databaseInit()(implicit system: ActorSystem) {

    db.withSession{ implicit session =>

      if (MTable.getTables("Tweets").list().isEmpty) {
//        Tweets.tweets.ddl.create
      }

    }
  }
}

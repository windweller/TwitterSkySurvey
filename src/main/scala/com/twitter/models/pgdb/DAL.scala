package com.twitter.models.pgdb

import akka.actor.{Props, ActorSystem}
import com.twitter.Config._
import MyPostgresDriver.simple._
import scala.slick.jdbc.meta.MTable


object DAL {
  val db =  Database.forURL(url = dbURL, user=dbUser, password=dbPassword, driver = dbDriver)

  def databaseInit()(implicit system: ActorSystem) = {

    db.withSession {
      implicit session =>

      if (MTable.getTables("Tweets").list.isEmpty) {
        Tweets.tweets.ddl.create
      }

    }

  }
}

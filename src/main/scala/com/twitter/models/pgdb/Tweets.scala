package com.twitter.models.pgdb

import org.json4s._
import MyPostgresDriver.simple._
import MyPostgresDriver.jsonMethods._

//table tweets
//created_at
//favorite_count
//id
//lang*
//retweet_count
//text*
//place_coorinates*
//place_country*
//place_fullName*
//user_screenname
//user_followers_count
//user_description*
//user_favCount
//user_location*
//user_utc_offset*
//user_timezone*
//
//tweets_hashtags: array[String]
//
//
//place, user—json


object Tweets {

  case class Tweet(id: Option[Int], created_at: String, favorite_count: Int, lang: Option[String], retweet_count: Int,
                    text: Option[String], place: Option[JValue], user: Option[JValue], tweets_hashtags: Option[List[String]])


  class TweetTable(tag: Tag) extends Table[Tweet](tag, "Tweets") {
    def id = column[Option[Int]]("TWEET_ID", O.PrimaryKey, O.AutoInc)
    def created_at = column[String]("TWEET_CREATED_AT")
    def favorite_count = column[Int]("TWEET_FAV_COUNT")
    def lang = column[Option[String]]("TWEET_LANG", O.Nullable)
    def retweet_count = column[Int]("TWEET_RETWE_COUNT")
    def text = column[Option[String]]("TWEET_LANG", O.Nullable, O.DBType("TEXT"))
    def place = column[Option[JValue]]("TWEET_PLACE", O.Nullable, O.DBType("JSON"))
    def user = column[Option[JValue]]("TWEET_USER", O.Nullable, O.DBType("JSON"))
    def tweets_hashtags = column[Option[List[String]]]("TWEET_HASH_TAG", O.Nullable)

    def * = (id, created_at, favorite_count, lang, retweet_count, text, place, user, tweets_hashtags) <> (Tweet.tupled, Tweet.unapply)
  }

  val tweets = TableQuery[TweetTable]

  def insert(tweet: Tweet)(implicit s: Session): Tweet = {
    val tweetId = tweets returning tweets.map(_.id) += tweet
    tweet.copy(id = tweetId)
  }
}

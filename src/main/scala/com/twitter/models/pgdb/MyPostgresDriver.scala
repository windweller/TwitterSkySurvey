package com.twitter.models.pgdb

import slick.driver.PostgresDriver
import com.github.tminglei.slickpg._


trait MyPostgresDriver extends PostgresDriver
with PgArraySupport
with PgDateSupport
with PgRangeSupport
with PgHStoreSupport
with PgSearchSupport
with PgJson4sSupport{

  override val Implicit = new ImplicitsPlus {}
  override val simple = new SimpleQLPlus {}

  trait ImplicitsPlus extends Implicits
  with ArrayImplicits
  with DateTimeImplicits
  with RangeImplicits
  with HStoreImplicits
  with JsonImplicits
  with SearchImplicits

  trait SimpleQLPlus extends SimpleQL
  with ImplicitsPlus
  with SearchAssistants
}

object MyPostgresDriver extends MyPostgresDriver {

  /// for json support
  type DOCType = text.Document
  override val jsonMethods = org.json4s.native.JsonMethods
  override lazy val Implicit = new Implicits with JsonImplicits
  override val simple = new Implicits with SimpleQL with JsonImplicits {
       implicit val strListTypeMapper = new SimpleArrayListJdbcType[String]("text")
  }
}

name := "SkySurveyTwitter"

version := "1.0"

scalaVersion  := "2.10.3"

val buildSettings = Defaults.defaultSettings ++ Seq(
  javaOptions += "-Xmx4G -Xms4G"
)

mainClass in (Compile, run) := Some("com.twitter.Boot")

resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype snapshots"  at "https://oss.sonatype.org/content/repositories/snapshots/")

libraryDependencies ++= {
  val akkaV = "2.1.4"
  val sprayV = "1.1.1"
  Seq(
    "io.spray"            %   "spray-can"     % sprayV,
    "io.spray"            %   "spray-routing" % sprayV,
    "io.spray"            %%  "spray-json"    % "1.2.6",
    "org.json4s"          %% "json4s-native"  % "3.2.4",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %% "akka-slf4j"     % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.scalatest"       %% "scalatest"       % "2.1.5"  % "test",
    //add-ons
    "com.github.nscala-time" %% "nscala-time" % "1.2.0",
    "com.github.tototoshi" %% "scala-csv" % "1.0.0",
    //add-ons-NLP
    "edu.stanford.nlp" % "stanford-corenlp" % "3.3.1",
    "cc.mallet" % "mallet" % "2.0.7",
    //add-ons-Twitter
    "org.twitter4j" % "twitter4j-core" % "4.0.2",
    "org.twitter4j" % "twitter4j-stream" % "4.0.2",
  //database
    "com.typesafe.slick" %%  "slick"  % "2.1.0",
    "org.postgresql" % "postgresql" % "9.3-1102-jdbc41",
    "com.github.tminglei" %% "slick-pg" % "0.6.3",
    "com.github.tminglei" %% "slick-pg_json4s" % "0.6.3"
  )
}
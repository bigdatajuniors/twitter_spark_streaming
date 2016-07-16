name := "twitter_spark_streaming"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.4.1",
  "org.apache.spark" % "spark-streaming_2.11" % "1.4.1",
  "org.apache.spark" % "spark-streaming-twitter_2.11" % "1.4.1",
  "org.twitter4j" % "twitter4j-stream" % "3.0.3",
  "com.google.code.gson" % "gson" % "2.6.2"
)
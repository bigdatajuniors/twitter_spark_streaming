package com.twitter.spark.streaming

/**
  * Created by sivapradip on 15/07/16.
  */

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Calculates popular hashtags (topics) over sliding 10 and 60 second windows from a Twitter
  * stream. The stream is instantiated with credentials and optionally filters supplied by the
  * command line arguments.
  *
  * Run this on your local machine as
  *
  */
object TwitterDemo extends App {
  val sparkConf = new SparkConf().setMaster("local[2]").setAppName("TwitterPopularTags")
  val ssc = new StreamingContext(sparkConf, Seconds(2))

  //To suppress Info messages
  val logger = Logger.getRootLogger();
  logger.setLevel(Level.ERROR)

  val stream = TwitterUtils.createStream(ssc, None, Array("Brexit"))

  Utils.print_top_n(stream, 10)

  ssc.start()
  ssc.awaitTermination()
}
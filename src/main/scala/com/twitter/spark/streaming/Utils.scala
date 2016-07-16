package com.twitter.spark.streaming

/**
  * Created by sivapradip on 15/07/16.
  */

import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import twitter4j.Status

object Utils {

  def print_top_n(stream: ReceiverInputDStream[Status], n : Int): Unit = {
    val hashTags = stream.flatMap(status => status.getText.split(" ").filter(_.startsWith("#")))
    val topCounts60 = hashTags.map((_, 1)).reduceByKeyAndWindow(_ + _, Seconds(n))
      .map { case (topic, count) => (count, topic) }
      .transform(_.sortByKey(false))

    // Print popular hashtags
    topCounts60.foreachRDD(rdd => {
      val topList = rdd.take(10)
      println("\nPopular topics in last 60 seconds (%s total):".format(rdd.count()))
      topList.foreach { case (count, tag) => println("%s (%s tweets)".format(tag, count)) }
    })
  }
}

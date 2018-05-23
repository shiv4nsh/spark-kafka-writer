package com.foobar

import com.foobar.config.AppConfig
import com.foobar.kafka.KafkaUtil
import com.foobar.spark.SparkUtil


/**
  * @author Shivansh <shiv4nsh@gmail.com>
  * @since 22/5/18
  */


object Boot extends KafkaUtil {

  import AppConfig._
  import com.foobar.env.AppEnv._

  val sparkUtil = new SparkUtil(sparkSession)

  def main(args: Array[String]): Unit = {
    val readDF = sparkUtil.isMidFilePresent().getOrElse(sparkUtil.readTextFile(fileToBeRead))
    readDF.cache()
    println("Reading successfully finished")
    //Writing the dataFrame to kafka
    writeDFToKafka(readDF, kafkaConf.url, kafkaConf.topic)
    println("Writing to Kafka Finished")
  }
}

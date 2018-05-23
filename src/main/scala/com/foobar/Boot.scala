package com.foobar

import com.foobar.config.AppConfig
import org.apache.spark.sql.{DataFrame, SaveMode}

import scala.util.Try


/**
  * @author Shivansh <shiv4nsh@gmail.com>
  * @since 22/5/18
  */


object Boot {

  import AppConfig._
  import com.foobar.env.AppEnv._
  import org.apache.spark.sql.functions._

  val TEMP_WRITE_PATH = tempFilePath

  def main(args: Array[String]): Unit = {
    val readDF = isMidFilePresent().getOrElse(readTextFile(fileToBeRead))
    readDF.cache()

    println("Reading successfully finished")
    //Writing the dataFrame to kafka
    writeDFToKafka(readDF)
    println("Writing to Kafka Finished")

    sys.addShutdownHook(spark.sparkContext.stop())
  }

  private def writeDFToKafka(df: DataFrame) = {

    df.write
      .format("kafka")
      .option("kafka.bootstrap.servers", kafka.url)
      //With the introduction of idempotent producer in Kafka 0.11 we are enabling this feature so in case if the spark
      //job fails and writes it multiple times then also the message will be written only once in the kafka
      .option("kafka.enable.idempotence", "true")
      .option("topic", kafka.topic)
      .save()
  }

  private def readTextFile(path: String, writePath: String = TEMP_WRITE_PATH) = {
    val readDF = spark.read.textFile(path)
      .toDF("value").withColumn("key", monotonically_increasing_id())
      .sort("key")
    readDF.write.mode(SaveMode.Overwrite).csv(writePath)
    readDF
  }

  private def isMidFilePresent(path: String = TEMP_WRITE_PATH): Option[DataFrame] = {
    Try(spark.read.option("header", "true").csv(path).toDF()).toOption
  }
}

package com.foobar

import org.apache.spark.sql.SparkSession

/**
  * @author Shivansh <shiv4nsh@gmail.com>
  * @since 22/5/18
  */

object SparkApp {
  @transient val spark = SparkSession.builder()
    .appName("Spark-Kafka-Writer")
    .master("local[*]")
    .getOrCreate()
}

object Boot {

  import SparkApp._

  def main(args: Array[String]): Unit = {

    import org.apache.spark.sql.functions._
    val readDF = spark.read.textFile("/home/shiv4nsh/IdeaProjects/facility-calculator").toDF("lines")
    readDF
      .select(to_json(struct("*")) as 'value)
      .write
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("topic", "writeline")
      .option("checkpointLocation", "/home/shiv4nsh/checkpoint")
      .save()

  }
}

package com.foobar.spark

import com.foobar.config.AppConfig
import org.apache.spark.sql.functions.monotonically_increasing_id
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

import scala.util.Try

/**
  * @author Shivansh <shiv4nsh@gmail.com>
  * @since 23/5/18
  */
class SparkUtil(sparkSession: SparkSession) {

  def isMidFilePresent(path: String = AppConfig.tempFilePath): Option[DataFrame] = {
    Try(sparkSession.read.csv(path).toDF("key","value")).toOption
  }

  def readTextFile(path: String, writePath: String = AppConfig.tempFilePath) = {
    val readDF = sparkSession.read.textFile(path)
      .toDF("value").withColumn("key", monotonically_increasing_id())
      .sort("key")
    readDF.write.mode(SaveMode.Overwrite).csv(writePath)
    readDF
  }
}

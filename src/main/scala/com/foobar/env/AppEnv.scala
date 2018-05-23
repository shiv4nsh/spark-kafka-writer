package com.foobar.env

import org.apache.spark.sql.SparkSession

/**
  * @author Shivansh <shiv4nsh@gmail.com>
  * @since 23/5/18
  */
object AppEnv{

  import com.foobar.config.AppConfig._

  @transient val spark = SparkSession.builder()
    .appName(appName)
    .master("local[*]")
    .getOrCreate()
  spark.sparkContext.setCheckpointDir(checkPointDirectory)
}
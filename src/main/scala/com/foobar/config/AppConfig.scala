package com.foobar.config

import com.typesafe.config.{Config, ConfigFactory}

/**
  * @author Shivansh <shiv4nsh@gmail.com>
  * @since 22/5/18
  */
object AppConfig {

  private val config: Config = ConfigFactory.load()
  val appName = config.getString("appName")
  val fileToBeRead = config.getString("fileToBeRead")
  val checkPointDirectory = config.getString("checkpointDir")
  val tempFilePath = config.getString("tempFilePath")
  val kafka = new KafkaConfig(config.getConfig("kafka"))
}

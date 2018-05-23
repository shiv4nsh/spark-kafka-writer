package com.foobar.config

import com.typesafe.config.ConfigFactory

/**
  * @author Shivansh <shiv4nsh@gmail.com>
  * @since 22/5/18
  */
object AppConfig {

  private val config = ConfigFactory.load()
  val fileToBeRead = config.getString("fileToBeRead")
  val checkPointDirectory = config.getString("checkpointDir")
}

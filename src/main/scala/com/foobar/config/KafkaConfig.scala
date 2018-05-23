package com.foobar.config

import com.typesafe.config.Config

/**
  * @author Shivansh <shiv4nsh@gmail.com>
  * @since 23/5/18
  */
class KafkaConfig(config: Config) {
  val url = config.getString("url")
  val topic = config.getString("topic")
}

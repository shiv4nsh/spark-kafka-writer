package com.foobar.kafka

import org.apache.spark.sql.DataFrame

/**
  * @author Shivansh <shiv4nsh@gmail.com>
  * @since 23/5/18
  */
trait KafkaUtil {


  def writeDFToKafka(df: DataFrame, url: String, topic: String) = {

    df.write
      .format("kafka")
      .option("kafka.bootstrap.servers", url)
      //With the introduction of idempotent producer in Kafka 0.11 we are enabling this feature so in case if the spark
      //job fails and writes it multiple times then also the message will be written only once in the kafka
      .option("kafka.enable.idempotence", "true")
      .option("topic", topic)
      .save()
  }
}

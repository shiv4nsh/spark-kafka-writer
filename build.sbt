name := "spark-kafka-writer"

version := "0.1"

scalaVersion := "2.11.8"

val sparkVersion = "2.2.0"
libraryDependencies ++= Seq(
  //Spark related dependencies
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-sql-kafka-0-10" % sparkVersion,
  "org.apache.kafka" % "kafka-clients" % "0.10.2.0",
  //config related dependencies
  "com.typesafe" % "config" % "1.3.1",
  //Test related dependencies
  "org.scalatest" %% "scalatest" % "3.0.0" % Test
)
        
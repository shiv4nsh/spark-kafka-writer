name := "spark-kafka-writer"

version := "0.1"

scalaVersion := "2.11.8"

val sparkVersion = "2.2.0"
libraryDependencies ++= Seq(
  //Spark related dependencies
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-sql-kafka-0-10" % sparkVersion,
  "org.apache.kafka" % "kafka-clients" % "0.11.0.2",
  //config related dependencies
  "com.typesafe" % "config" % "1.3.1",
  //Test related dependencies
  "org.scalatest" %% "scalatest" % "3.0.0" % Test,
  "com.holdenkarau" %% "spark-testing-base" % "2.2.0_0.9.0" % Test
)


parallelExecution in Test := false
fork in Test := true
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")
package com.foobar.spark

import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.FunSuite

/**
  * @author Shivansh <shiv4nsh@gmail.com>
  * @since 23/5/18
  */
class SparkUtilTest extends FunSuite with SharedSparkContext {

  import org.apache.spark.sql.SQLContext

  test("Read all the files in a folder") {
    val sqlContext = new SQLContext(sc)
    val sparkSession = sqlContext.sparkSession
    val sparkUtil = new SparkUtil(sparkSession)
    val tempPath = "src/test/resources/temp"
    val data = sparkUtil.readTextFile("src/test/resources/readData", tempPath)
    data.count()
    val dataDF = sparkSession.read.csv(tempPath).toDF("key", "value")
    val dataPresent = dataDF.rdd.map(_.getString(0))
    val checkData = dataPresent.collect().sorted
    val expectedData = data.rdd.map(_.getString(0)).collect().sorted
    assert(data.count() == 13 && checkData.toList === expectedData.toList)
  }
  test("Is mid file present") {
    val sqlContext = new SQLContext(sc)
    val sparkSession = sqlContext.sparkSession
    val sparkUtil = new SparkUtil(sparkSession)
    val tempPath = "src/test/resources/temp"
    val file = sparkUtil.isMidFilePresent(tempPath)
    assert(file.isDefined && file.get.count() == 13)
  }
}
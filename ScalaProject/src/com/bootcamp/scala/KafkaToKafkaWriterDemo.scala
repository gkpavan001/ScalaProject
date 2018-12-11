package com.bootcamp.scala

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.ForeachWriter

object KafkaToKafkaWriterDemo {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.
      master("local")
      .appName("KafkaJSONWriter")
      .getOrCreate()
    import spark.implicits._
    spark.sparkContext.setLogLevel("ERROR")

    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "mySQLIncrementData-employee")
      .option("startingOffsets", "earliest")
      .load()

    val data = df.selectExpr("CAST(value AS STRING)")
      .as[(String)]
    
    // We can perform any kind of computation on "data" above
    //and store the final data to kafka like below
    
    
    val query = data.writeStream.format("kafka")
      .outputMode("append")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("topic","kafkaEmpSink") // Topic name can be anything
      .option("checkpointLocation", "C:/CheckPoint")
      .start()

    query.awaitTermination()
  }

}
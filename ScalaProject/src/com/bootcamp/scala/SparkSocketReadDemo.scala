package com.bootcamp.scala

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.OutputMode

// This class is used to read data from socket stream and compute
object SparkSocketReadDemo{
	def main(args: Array[String]) {
		val sparkSession = SparkSession.builder
				.master("local")
				.appName("example")
				.getOrCreate()
				val socketStreamDF = sparkSession.readStream
				.format("socket")
				.option("host", "localhost")
				.option("port", 50050)
				.load()
				sparkSession.sparkContext.setLogLevel("ERROR")
				import sparkSession.implicits._
				
				val words = socketStreamDF.as[String].flatMap(_.split(" "))
				val wordCounts = words.groupBy("value").count()

				val consoleDataFrameWriter = wordCounts.writeStream
				.format("console")
				.outputMode("complete")

				val query = consoleDataFrameWriter.start()

				query.awaitTermination()
	}
}



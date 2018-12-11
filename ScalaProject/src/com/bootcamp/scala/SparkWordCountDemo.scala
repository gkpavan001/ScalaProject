package com.bootcamp.scala

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark._
import java.io.FileWriter
import java.io.BufferedWriter

object SparkWordCountDemo {
	def main(args: Array[String]) {
		val conf = new SparkConf().setMaster("local").setAppName("Word Count")
		val sc = new SparkContext(conf)
		sc.setLogLevel("ERROR")
		val inputfile = sc.textFile("file:\\C:\\bootcamp\\WordCountFileInput.txt")
		val counts = inputfile.flatMap(line => line.split(" ")).map(word =>(word, 1)).reduceByKey(_+_)
		counts.foreach(println)
		counts.saveAsTextFile("file:\\C:\\WordCountFileOutput")
		sc.stop()
	}
}
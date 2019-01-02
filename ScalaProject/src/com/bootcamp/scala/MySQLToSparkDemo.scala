package com.bootcamp.scala

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkConf

object MySQLToSparkDemo {
	def main(args: Array[String]): Unit = {
			val sparkConf = new SparkConf()
					.setAppName("MySQL To Spark Demo")
					.setMaster("local[2]")
					val sc = new SparkContext(sparkConf)
					sc.setLogLevel("ERROR")
					val sqlcontext = new SQLContext(sc)
					val dataframe_mysql = sqlcontext.read.format("jdbc")
					.option("url", "jdbc:mysql://localhost:3306/bootcamp")
					.option("driver", "com.mysql.jdbc.Driver")
					.option("dbtable", "employee")
					.option("user", "root")
					.option("password", "1234").load()
					dataframe_mysql.show()
	}
}
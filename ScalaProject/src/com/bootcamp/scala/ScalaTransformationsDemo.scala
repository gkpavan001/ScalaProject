package com.bootcamp.scala

object ScalaTransformationsDemo {
case class employee(number:Int, name:String,salary:Int)
def main(args: Array[String]): Unit = {
		val newData = "struct{number=10,name=John,salary=9580}"
		val n =	newData.substring(7,newData.toString().length()-1)
		val nData =	newData.substring(7,newData.toString().length()-1).split("\n")
				.map( lines => {
					var line = lines.toString.split(",")
					var e=  employee(line(0).split("=")(1).toInt,
							line(1).split("=")(1),
							line(2).split("=")(1).toInt)
							println(e);
				})
}
}





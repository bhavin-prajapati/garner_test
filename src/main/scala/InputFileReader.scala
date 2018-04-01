/**
 * Created by bhavinprajapati on 15-12-15.
 */
package com.garner

import scala.io.Source

object InputFileReader {
  def readFile(fileName: String): List[Array[String]] = {
    var returnList = List[Array[String]]()
    var folder = "main";
    if(Configuration.isUnitTesting) folder = "test"
    val src = Source.fromFile("src/"+folder+"/resources/" + fileName)
    val iter = src.getLines().map(_.split(","))
    iter.drop(1).foreach(line => {
      returnList =  line :: returnList
    })
    src.close()
    return returnList;
  }
}

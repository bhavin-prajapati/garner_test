/**
 * Created by bhavinprajapati on 15-12-15.
 */
package com.garner

import java.io.File

class OutputFileWriter(fileName: String) {
  var folder = "main";
  if(Configuration.isUnitTesting) folder = "test"
  val file:File = new File("src/"+folder+"/resources/" + fileName)

  def printToFile()(op: java.io.PrintWriter => Unit) {
    val p = new java.io.PrintWriter(file)
    try { op(p) } finally { p.close() }
  }
}

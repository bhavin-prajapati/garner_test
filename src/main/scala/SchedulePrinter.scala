package com.garner

import java.util

/**
 * Created by bhavinprajapati on 15-12-23.
 */
object SchedulePrinter {
  def printSchedule(scheduledShipments:util.ArrayList[ScheduledShipment], fileName: String): Unit = {
    val out:OutputFileWriter = new OutputFileWriter(fileName);
    out.printToFile(){p =>
      var header = "day,hour,slot,id";
      println(header);
      p.println(header)
      for( i <- 0 to scheduledShipments.size() - 1 ){
        val yearFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        val hourFormat = new java.text.SimpleDateFormat("hh-mm");
        var line = yearFormat.format(scheduledShipments.get(i)._day) + "," + hourFormat.format(scheduledShipments.get(i)._day) + "," + scheduledShipments.get(i)._slot + "," + scheduledShipments.get(i)._id;
        println(line);
        p.println(line)
      }
    }
  }
}

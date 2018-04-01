/**
 * Created by bhavinprajapati on 15-12-15.
 */
package com.garner

import java.io.{IOException, FileNotFoundException}

object IceRoad {
  def main(args: Array[String]) {
    println("Garner Ice Road Test");
    println("--------------------");
    var ok = true
    while (ok) {
      printMenu();
      val ln = readLine()
      ok = ln != null
      if (ok) {
        ln match {
          case "1" => start(args);
          case "2" => ok = false;
        }
      }
    }
    println("Done!");
  }

  def printMenu(): Unit = {
    println("\n1. Output Schedule");
    println("2. Quit");
    print("=> ");
  }

  def start(args: Array[String]) {
    if(args.length == 2) {

      println("\nReading shipments from " + args(0) + "...");
      try{
        val shipmentList = InputFileReader.readFile(args(0));

        println("id,description,weight,unit,priority")
        shipmentList.foreach(line => println(line.foreach(str => print(str + " "))))
        val shipments = ShipmentParser.parseShipments(shipmentList);

        println("\nCalculating schedule...");
        val scheduledShipments = ShipmentScheduler.scheduleShipments(shipments)

        println("\nWriting shipment schedule to " + args(1) + "...");
        SchedulePrinter.printSchedule(scheduledShipments, args(1));

      } catch {
        case ex: FileNotFoundException =>{
          println("Missing file exception: " + args(0))
        }
        case ex: IOException => {
          println("IO Exception")
        }
      }
    } else {
      println("Invalid arguments");
    }
  }
}
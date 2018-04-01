/**
 * Created by bhavinprajapati on 15-12-15.
 */
package com.garner

import scala.collection.mutable.ListBuffer

object ShipmentParser {
  def parseShipments( shipments: List[Array[String]]): Array[Shipment] = {
    val shipmentList = ListBuffer[Shipment]();
    var ids:Set[Int] = Set.empty;

    shipments.foreach(item => {
        var shipment:Shipment = null

        for( i <- 0 to 3 ){
          if(item(i).length() == 0) throw new MissingValueException("Missing values in input file.")
        }

        try {
          if (item.length == 5) {
            shipment = new Shipment(item(0).toInt, item(1), item(2).toDouble, item(3), item(4).toInt);
          } else if (item.length == 4) {
            shipment = new Shipment(item(0).toInt, item(1), item(2).toDouble, item(3), 4);
          } else {
            throw new IncorrectFileFormatException("Incorrectly formatted input file.")
          }
        } catch {
          case ex: NumberFormatException => {
            throw new IncorrectValueTypeException("Incorrectly value type in input file.")
          }
        }

        if(ids.contains(item(0).toInt)) throw new DuplicateIDException("Duplicate IDs in input file.")
        ids += item(0).toInt

        shipmentList += shipment
      }
    )
    return shipmentList.toArray;
  }
}






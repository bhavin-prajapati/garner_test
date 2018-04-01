/**
 * Created by bhavinprajapati on 15-12-16.
 */
package com.garner

import java.util
import java.util.{Calendar, Date}

object ShipmentScheduler {

  var date:Date = new Date(115, 1, 1, 8, 0)
  var limit:Date = new Date(115, 1, 16, 8, 0)
  var slot = 1;

  def addOneHour(): Date = {
    val cal:Calendar = Calendar.getInstance();
    cal.setTime(this.date);
    cal.add(Calendar.HOUR_OF_DAY, 1);
    return cal.getTime();
  }

  def updateSlot() = {
    if(this.slot > 7) {
      this.slot = 1;
      this.date = addOneHour();
    }
  }

  def scheduleShipments( shipments: Array[Shipment]): util.ArrayList[ScheduledShipment] = {
    val returnSchedule = new util.ArrayList[ScheduledShipment]
    date = new Date(115, 1, 1, 8, 0);
    slot = 1;
    shipments.foreach(shipment => {
      if(!(shipment._unit == "lbs" | shipment._unit == "ton" | shipment._unit == "kg")) throw new UnknownWeightTypeException("Unknown weight type: " + shipment._unit);
      if(shipment._weight < 0) throw new NegativeWeightException("Negative weight in input file: " + shipment._weight.toString() + ". Weight should always be positive.")
      if(shipment._unit == "lbs") {
        shipment._weight = shipment._weight/2000
        shipment._unit = "ton"
      } else if(shipment._unit == "kg") {
        shipment._weight = shipment._weight/907.185
        shipment._unit = "ton"
      }
    })

    val shipmentWeight = shipments.groupBy {
      case lightShipment if lightShipment._weight <= 15 && lightShipment._unit == "ton" => "light"
      case heavyShipment if heavyShipment._weight > 15 && heavyShipment._unit == "ton" => "heavy"
    }

    val lightShipment = shipmentWeight.get("light").head.sortBy(s =>
      s._priority -> s._weight
    )

    val remainingLightShipments:util.ArrayList[Shipment] = new util.ArrayList[Shipment];

    lightShipment.foreach(shipment => {
      if (date.before(limit)) {
        updateSlot()
        returnSchedule.add(new ScheduledShipment(date, slot, shipment._id))
        slot += 1
      } else {
        remainingLightShipments.add(shipment);
      }
    })

    shipmentWeight.get("heavy").head.foreach(shipment => {
      remainingLightShipments.add(shipment)
    })

    val remainingShipments:Array[Shipment] = remainingLightShipments.toArray(new Array[Shipment](remainingLightShipments.size())).sortBy(s =>
      s._priority -> 1/s._weight
    )

    remainingShipments.foreach(shipment =>
    {
      updateSlot()
      returnSchedule.add( new ScheduledShipment(date, slot, shipment._id))
      slot += 1
    })

    return returnSchedule;
  }
}

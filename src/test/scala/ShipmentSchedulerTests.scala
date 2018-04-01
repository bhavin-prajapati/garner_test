import java.util.Date

import com.garner._
import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class ShipmentSchedulerTests extends FlatSpec with ShouldMatchers {
  com.garner.Configuration.isUnitTesting = true;

  "Normal input.csv" should "read the input.csv file, parses and schedules shipments. Checks to ensure number of scheduled items are same as parsed items." in {
    val shipmentList = InputFileReader.readFile("input.csv");
    val shipments = ShipmentParser.parseShipments(shipmentList);
    val scheduledShipments = ShipmentScheduler.scheduleShipments(shipments)
    scheduledShipments.size() should be (shipmentList.length)
  }

  "Unknown weight type in unknown_weight_type.csv. eg grams instead of kg or pounds instead of lbs" should "read the unknown_weight_type.csv file and throws an UnknownWeightTypeException" in {
    val exception = intercept[UnknownWeightTypeException]{
      val shipmentList = InputFileReader.readFile("unknown_weight_type.csv");
      val shipments = ShipmentParser.parseShipments(shipmentList);
      val scheduledShipments = ShipmentScheduler.scheduleShipments(shipments)
    }
  }

  "Negative weight in negative_weight.csv. eg grams instead of kg or pounds instead of lbs" should "read the negative_weight.csv file and throws an NegativeWeightException" in {
    val exception = intercept[NegativeWeightException]{
      val shipmentList = InputFileReader.readFile("negative_weight.csv");
      val shipments = ShipmentParser.parseShipments(shipmentList);
      val scheduledShipments = ShipmentScheduler.scheduleShipments(shipments)
    }
  }

  "Long list of shipments in long.csv to ensure 15 day rule works" should "read the long.csv file and check scheduled shipments in first 15 days are under 15 tons." in {
    val shipmentList = InputFileReader.readFile("long.csv");
    var shipments = ShipmentParser.parseShipments(shipmentList);
    val scheduledShipments = ShipmentScheduler.scheduleShipments(shipments)

    shipments.foreach(shipment => {
      if(shipment._unit == "lbs") {
        shipment._weight = shipment._weight/2000
        shipment._unit = "ton"
      } else if(shipment._unit == "kg") {
        shipment._weight = shipment._weight/907.185
        shipment._unit = "ton"
      }
    })

    var limit:Date = new Date(115, 1, 16, 8, 0)
    for( i <- 0 to scheduledShipments.size() - 1 ){
      if(scheduledShipments.get(i)._day.before(limit)) {
        var shipment = shipments.toList.find(shipment => shipment._id == scheduledShipments.get(i)._id)
        if(shipment != null) shipment.head._weight should be <= 15.toDouble
      }
    }
  }
}
import com.garner._
import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class ShipmentParserTests extends FlatSpec with ShouldMatchers {
  com.garner.Configuration.isUnitTesting = true;

  "Normal input.csv" should "read the input.csv file and parse the shipments" in {
    val shipmentList = InputFileReader.readFile("input.csv");
    val shipments = ShipmentParser.parseShipments(shipmentList);
    shipments.length should be (shipmentList.length)
  }

  "Incorrectly formatted incorrect1.csv" should "attempt to read the incorrect1.csv file and throw an IncorrectFileFormatException" in {
    val exception = intercept[IncorrectFileFormatException]{
      val shipmentList = InputFileReader.readFile("incorrect1.csv");
      val shipments = ShipmentParser.parseShipments(shipmentList);
    }
  }

  "Incorrectly formatted incorrect2.csv" should "attempt to read the incorrect2.csv file and throw an IncorrectFileFormatException" in {
    val exception = intercept[IncorrectFileFormatException]{
      val shipmentList = InputFileReader.readFile("incorrect2.csv");
      val shipments = ShipmentParser.parseShipments(shipmentList);
    }
  }

  "Missing values in input file missing.csv" should "attempt to read the missing.csv file and throw an MissingValuesException" in {
    val exception = intercept[MissingValueException]{
      val shipmentList = InputFileReader.readFile("missing.csv");
      val shipments = ShipmentParser.parseShipments(shipmentList);
    }
  }

  "Duplicate IDs in input file duplicateID.csv" should "attempt to read the duplicateID.csv file and throw an DuplicateIDsException" in {
    val exception = intercept[DuplicateIDException]{
      val shipmentList = InputFileReader.readFile("duplicateID.csv");
      val shipments = ShipmentParser.parseShipments(shipmentList);
    }
  }

  "Wrong value type in input file incorrect3.csv. eg. String instead of Int" should "attempt to read the incorrect3.csv file and throw an IncorrectValueTypeException" in {
    val exception = intercept[IncorrectValueTypeException]{
      val shipmentList = InputFileReader.readFile("incorrect3.csv");
      val shipments = ShipmentParser.parseShipments(shipmentList);
    }
  }
}
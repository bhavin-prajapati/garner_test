import java.io.FileNotFoundException

import com.garner.InputFileReader
import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class InputFileReaderTests extends FlatSpec with ShouldMatchers {
  com.garner.Configuration.isUnitTesting = true;

  "Normal input.csv" should "read the input.csv file and length should not be 0" in {
    val shipmentList = InputFileReader.readFile("input.csv");
    shipmentList.length should not be (0)
  }

  "Empty input.csv" should "read the empty.csv file and length should be 0" in {
    val shipmentList = InputFileReader.readFile("empty.csv");
    shipmentList.length should be (0)
  }

  "Missing file.csv" should "attempt to read the file.csv and throw an FileNotFoundException" in {
    val exception = intercept[FileNotFoundException]{
      val shipmentList = InputFileReader.readFile("file.csv");
    }
  }
}
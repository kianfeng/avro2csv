package utils;
import java.io.File;

public class CustomerCsvToAvro
{
  public static void main(String[] args) throws Exception
  {
    String baseFolder = "./";

    String outputFilename = "customer_test_1.avro";
    String schemaFilename = "src/MyCommandLine/avro/customer.avsc";

    File schemaFile = new File( baseFolder + schemaFilename);

    String csvData_1 = "Peter;Lawson;46;7.1;80;false";
    String csvData_2 = "Linda;Betson;34;7.3;69;false";
    String csvData_3 = "Beth;;;;;false";


    CsvToAvroGenericWriter writer = new CsvToAvroGenericWriter(schemaFile,baseFolder + outputFilename,CsvToAvroGenericWriter.MODE_WRITE);
    //read as each line as string -> convert into String[] -> add value into apache.record -> check record valid -> write record into fileWriter
    writer.append(csvData_1);
    writer.append(csvData_2);
    writer.append(csvData_3);
    writer.closeWriter();
  }
}
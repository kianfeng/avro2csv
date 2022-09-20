
// Java Program to Write Contents of JSONArray to a CSV File

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.File;
import java.io.IOException;

public class json2csv0919{
  public static void jsonToCsv() throws IOException  {
    //get jsonTree/Node
    File jsonInputFile = new File("/Users/fqmark/IdeaProjects/JPMC/avro&csv/kian-avro2csv/data/iris.json");
    JsonNode jsonTree = new ObjectMapper().readTree(jsonInputFile);
    //using CsvSchema Builder object to define the columns of the csv File
    CsvSchema.Builder builder = CsvSchema.builder();
    jsonTree.elements().next().fieldNames().forEachRemaining(f -> builder.addColumn(f));
    //CsvSchemaBuilder -> SchemaObject
    CsvSchema csvSchema = builder.build().withHeader();
    //CsvMapper to convejsonTreert
    CsvMapper csvMapper = new CsvMapper();
    csvMapper.writer(csvSchema).writeValue(new File("/Users/fqmark/IdeaProjects/JPMC/avro&csv/kian-avro2csv/data/iris.csv"),jsonTree);

  }
  public void csvToJson(){

  }

  public static void main(String[] args) throws IOException {
    jsonToCsv();
  }
}
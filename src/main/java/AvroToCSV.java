import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.commons.io.FileUtils;

public class AvroToCSV {

  public static void readAvro(File file) {
    // Read Avro ,parse Schema to get field names and parse it to json
    try {
      /**
       * DatumReader:converts in-memory serialized items -> objects/class
       * DataFileReader: read file's schema and data
       * */
      //DatumWriter - converts Java objects into an in-memory serialized format
      GenericDatumReader<GenericData.Record> datum = new GenericDatumReader<>();
      //DatumReader - deserialize the data file
      DataFileReader<GenericData.Record> reader = new DataFileReader<>(file, datum);

      GenericData.Record record = new GenericData.Record(reader.getSchema());
      Schema schema = reader.getSchema();
      List<String> fieldValues = new ArrayList<>();
      JSONArray jsonArray = new JSONArray();
      for (Field field : schema.getFields()) {
        fieldValues.add(field.name());
      }

      //iteration
      while (reader.hasNext()) {
        reader.next(record);
        Map<String, String> jsonFileds = new HashMap<>();
        for (String item : fieldValues) {
          //System.out.println(item);

          jsonFileds.put(item, record.get(item).toString());
        }
        jsonArray.put(jsonFileds);
      }
      //System.out.println(jsonArray.toString());
      reader.close();
      jsonToCSV(jsonArray);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public static void jsonToCSV(JSONArray json) {
    File file = new File("twitter.csv");
    String csv;
    try {
      csv = CDL.toString(json);
      FileUtils.writeStringToFile(file, csv);
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    File f = new File("twitter.avro");
    readAvro(f);
  }
}
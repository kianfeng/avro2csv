import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

class CsvToJsonTest {

  @Test
  void TestCsvToJson() throws IOException {
    String inputPath = "/Users/fqmark/IdeaProjects/JPMC/avro&csv/kian-avro2csv/data/simple.csv";
    String outputPath = "/Users/fqmark/IdeaProjects/JPMC/avro&csv/kian-avro2csv/data/hello.json";
    CsvToJson.csvToJson(inputPath,outputPath);
  }

  @Test
  void anohterWay() throws IOException {
    String inputPath = "/Users/fqmark/IdeaProjects/JPMC/avro&csv/kian-avro2csv/data/simple.csv";
    String outputPath = "/Users/fqmark/IdeaProjects/JPMC/avro&csv/kian-avro2csv/data/hello.json";

    String headerString = "FirstName,LastName,Age,Phone,Address,Friend.Name,Friend.Age,Friend.Phone,Friend.Address";

    InputStream inputStream = new FileInputStream(inputPath);
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    Stream<String> lines = bufferedReader.lines();
    String[] header = bufferedReader.readLine().split(",");
    List<String[]> valueList = new ArrayList<>();
    lines.forEach(
        line -> {
          String[] value = line.split(",");
          valueList.add(value);
        }
    );
    Map<String,Object> hashMap = new HashMap<>();
    List<String> stringLists = new ArrayList<>();
    for (int i=0; i< valueList.size(); i++){
      for (int j=0; j<header.length; j++){
        String name = header[j];
        String value = valueList.get(i)[j];
        addValue(hashMap, name.split("\\."), value);

        //totalMap.put()
        //print
      }
      ObjectMapper objectMapper = new ObjectMapper();
      String jsonString = objectMapper.writeValueAsString(hashMap);
      //System.out.println(jsonString);
      stringLists.add(jsonString);
    }
    stringLists.stream().forEach(
        stringlist -> {
          System.out.println(stringlist);
          System.out.println();
        }
    );
  }
  private static void addValue(Map<String,Object> map, String[] path,
      String value) {
    for (int i = 0; i < path.length-1; ++i) {
      String elem = path[i];
      Object current = map.get(elem);
      Map<String,Object> map2;
      if (current instanceof Map) {
        map2 = (Map<String,Object>)map.get(elem);
      } else {
        map2 = new HashMap<>();
        map.put(elem, map2);
      }
      map = map2;
    }
    map.put(path[path.length-1], value);
  }


  @Test
  void JackSonTest() throws IOException {
    String inputPath = "/Users/fqmark/IdeaProjects/JPMC/avro&csv/kian-avro2csv/data/simple.csv";
    String outputPath = "/Users/fqmark/IdeaProjects/JPMC/avro&csv/kian-avro2csv/data/hello2.json";
    File input = new File(inputPath);
    File output = new File(outputPath);
    CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
    CsvMapper csvMapper = new CsvMapper();
    // Read data from CSV file
    List<Object> readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();
    ObjectMapper mapper = new ObjectMapper();
    // Write JSON formated data to output.json file
    mapper.writerWithDefaultPrettyPrinter().writeValue(output, readAll);
    // Write JSON formated data to stdout
    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(readAll));
  }
}
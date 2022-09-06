import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class CsvToJson {

  public static void csvToJson(final String inputPath, final String outputPath) throws IOException {
    CSVReader reader = new CSVReader(new FileReader(inputPath));
    FileWriter file = new FileWriter(outputPath);

    List<String[]> csv = reader.readAll();
    if (csv.size() != 0) {
      JSONArray jsonArray = new JSONArray();
      List<String> headers = new ArrayList<String>();
      for (String s : csv.get(0)) {
        headers.add(s);
      }
      for (int i = 1; i < csv.size(); i++) {
        JSONObject jsonObject = new JSONObject();
        String[] currentLine = csv.get(i);
        for (int j = 0; j < currentLine.length; j++) {
          jsonObject.put(headers.get(j), currentLine[j]);
        }
        jsonArray.add(jsonObject);
      }
      file.write(jsonArray.toJSONString());
    }
    file.flush();
    file.close();
  }
}

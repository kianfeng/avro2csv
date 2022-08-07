package utils;
import com.avro.example.Twitter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
public class csvToAvro {
  public static void main(String[] args) throws Exception
  {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    System.out.println(df.format(new Date()) + " processing records started");

    String folder = "./";
    String outputFile = folder + "/discount_test.avro";

    File inputFile = new File("src/main/resources/twitter2.csv");
    FileReader fileReader = new FileReader(inputFile);
    BufferedReader bufferedReader = new BufferedReader(fileReader);

    long counter=0;
    String line;

    CsvToAvroWriter<Twitter> writer = new CsvToAvroWriter<Twitter>(Twitter.SCHEMA$,outputFile,CsvToAvroWriter.MODE_WRITE);

    while((line = bufferedReader.readLine()) != null)
    {
      counter ++;

      writer.append(new Twitter(), line);

      if(counter % 10000==0)
      {
        System.out.println(df.format(new Date()) + " processed records: " + counter);
      }
    }
    fileReader.close();
    writer.closeWriter();

    System.out.println(df.format(new Date()));
    System.out.println(df.format(new Date()) + " processing records complete");
    System.out.println(df.format(new Date()) + " total processed records: " + counter);
  }
}

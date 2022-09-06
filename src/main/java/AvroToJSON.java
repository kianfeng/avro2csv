import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import utils.CSVWriter;
import utils.JSONFlattener;

public class AvroToJSON {

  public static void main(String[] args) throws Exception {
    DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    System.out.println(timeStampPattern.format(java.time.LocalDateTime.now()));
//    AvroToJSON AvroToJSON = new AvroToJSON();
//    AvroToJSON.convertToCSV("twitter.avro","src/MyCommandLine/resources/test/");
  }

  public void convertToCSV(String inputAvroFileName, String csvOutputPath) throws Exception{
    try {
      if (isDirectory(inputAvroFileName)){
        try(
            Stream<Path> streamPaths = Files.list(Paths.get(inputAvroFileName))){
          streamPaths.filter(this::filterAvroFiles)
              .map(Path::toString)
              .forEach(inputFileName -> {
                try {
                  processAvro(inputFileName, csvOutputPath);
                } catch (IOException e) {
                  throw new RuntimeException(e);
                }
              });
        }
      }else {
        processAvro("src/MyCommandLine/resources/test/twitter.avro",csvOutputPath);
      }
    }catch (IOException ex){
      throw  new Exception("Conversion failed." + ex.getMessage());
    }

  }



  private boolean isDirectory(String inputAvroFileName) {
    Path file = new File(inputAvroFileName).toPath();
    return Files.isDirectory(file);
  }
  private void processAvro(String inputAvroFileName, String csvOutputPath) throws IOException {
    File inputAvroFile = new File(inputAvroFileName);
    String outputCsvFileName = generateOutputFileName(inputAvroFile,csvOutputPath);
    DataFileReader<GenericRecord> dataFileReader = avroFileReader(inputAvroFile);
    try(
        FileWriter fileWriter = new FileWriter(outputCsvFileName,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter)){
      GenericRecord record = null;
      int idx = 0;
      while (dataFileReader.hasNext()){
        record = dataFileReader.next(record);
        String csvString = record.toString();

        List<Map<String, String>> flatJson = JSONFlattener.parseJson(csvString);
        //CSVWriter.writeToFile(CSVWriter.getCSV(flatJson), "sample_string.csv");

        System.out.println(csvString);
        printWriter.println(CSVWriter.getCSV(flatJson,idx));
        idx++;
      }
    }catch (IOException ex){
      System.out.println("IO Exception "+ ex);
    }
  }
//  private void processAvro(String inputAvroFileName, String csvOutputPath) throws IOException {
//    File inputAvroFile = new File(inputAvroFileName);
//    String outputCsvFileName = generateOutputFileName(inputAvroFile,csvOutputPath);
//    DataFileReader<GenericRecord> dataFileReader = avroFileReader(inputAvroFile);
//
// {
//          GenericRecord record = null;
//          while (dataFileReader.hasNext()){
//            record = dataFileReader.next(record);
//            String jsonString = record.toString();
//
//            List<Map<String, String>> flatJson = JSONFlattener.parseJson(jsonString);
//            CSVWriter.writeToFile(CSVWriter.getCSV(flatJson), "sample_string.csv");
//          }
//        }
//  }
  private DataFileReader<GenericRecord> avroFileReader(File inputAvroFile) {
    DataFileReader<GenericRecord> dataFileReader = null;
    try {
      DatumReader<GenericRecord> datumReader = new GenericDatumReader<>();
      dataFileReader = new DataFileReader<>(inputAvroFile,datumReader);
    }catch (IOException ex){
      System.out.println(ex);
    }
    return dataFileReader;
  }
  private boolean filterAvroFiles(Path filePath) {
    File file = filePath.toFile();
    String fileName = file.getName();
    return file.isFile() && fileName.contains(".avro");
  }

  private String generateOutputFileName(File inputAvroFile, String csvOutputPath) {
    System.out.println("Filename "+ inputAvroFile.getName());
    String outputFileName = inputAvroFile.getName().split("\\.")[0].concat(".csv");
    return Paths.get(csvOutputPath,outputFileName).toString();
  }
}

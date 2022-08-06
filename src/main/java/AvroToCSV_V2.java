import java.util.ArrayList;
import java.util.List;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
public class AvroToCSV_V2 {

  public static void main(String[] args) throws Exception {
    AvroToCSV_V2 avroToCSV_v2 = new AvroToCSV_V2();
    avroToCSV_v2.convertToCSV("twitter.avro","./");
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
        processAvro(inputAvroFileName,csvOutputPath);
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
      Schema schema = dataFileReader.getSchema();
      List<String> fieldValues = new ArrayList<>();
      for (Field field : schema.getFields()) {
        fieldValues.add(field.name());
      }

      while (dataFileReader.hasNext()){
        record = dataFileReader.next(record);
        String csvString = record.toString();
        System.out.println(csvString);
        printWriter.println(csvString);
      }
    }catch (IOException ex){
      System.out.println("IO Exception "+ ex);
    }
  }
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

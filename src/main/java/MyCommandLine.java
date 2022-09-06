import java.io.File;
import org.apache.commons.cli.*;
import org.apache.commons.compress.utils.FileNameUtils;

public class MyCommandLine {
  private static final String DESCRIPTION = "Generate coverage and data validity report." ;
  private static void usage(Options options, String message) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("convert [OPTIONS] <file_name>", DESCRIPTION, options, message);
    System.exit(1);
  }

  public enum FileType {
    AVRO, PARQUET, CSV, JSON;
  }
  public static void exit(String message, int returnCode) {
    System.out.println(message);
    System.exit(returnCode);
  }
  public static void main(String[] args) {
    Options options = new Options();

    options.addOption("d", true, "CSV delimiter (if not specified ftest will try to guess)");
    options.addOption("h", false, "help");
    options.addOption("j", false, "convert to JSON (instead of generating coverage report");
    options.addOption("c", false, "convert to CSV (instead of generating coverage report");
    options.addOption("a", false, "convert to avro (instead of generating coverage report");
    CommandLineParser parser = new GnuParser();
    String fileName = null;
    try {
      CommandLine cli = parser.parse(options, args);
      if(cli.getArgs().length < 1) {
        usage(options, "<file_name> must be specified\n");
      }
      fileName = cli.getArgs()[0];
      System.out.println("fileName: "+fileName);

      boolean toCsv = cli.hasOption("c");
      boolean toJson = cli.hasOption("j");
      boolean toAvro = cli.hasOption("a");
      //todo: add -d delimiter checker

      FileType type = getFileType(fileName);

      if (type == FileType.AVRO && toCsv){
        System.out.println("convert avro to csv");
      }
      else if(type == FileType.CSV && toAvro){
        System.out.println("convert csv to avro");
      }else if (type == FileType.JSON && toAvro){
        System.out.println("convert JSON to Avro");
      }else if (type == FileType.AVRO && toJson) {
        System.out.println("convert avro to json");
      }else {
        System.out.println("There is no option we can convert");
      }
} catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  private static FileType getFileType(String fileName){
    String fileType= FileNameUtils.getExtension(fileName);
    System.out.println(fileType);
    FileType type = null;
    if (fileType.equals("csv")){
      type = FileType.CSV;
    }else if (fileType.equals("avro")){
      type = FileType.AVRO;
    }else if (fileType.equals("json")){
      type = FileType.JSON;
    }else {
      System.out.println("Please input valid Json File");
    }
    return type;
  }
}


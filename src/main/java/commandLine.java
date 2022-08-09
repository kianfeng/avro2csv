import org.apache.commons.cli.*;

public class commandLine {
  private static final String DESCRIPTION = "Generate coverage and data validity report." ;
  private static void usage(Options options, String message) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("cf [OPTIONS] <file_name>", DESCRIPTION, options, message);
    System.exit(1);
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
    try {
      CommandLine cli = parser.parse(options, args);
      if(cli.getArgs().length < 1) {
        usage(options, "<file_name> must be specified\n");
      }
      boolean toCsv = cli.hasOption("c");
      boolean toJson = cli.hasOption("j");
      boolean toAvro = cli.hasOption("a");
      //todo: add -d delimiter checker

      if (toCsv){
        System.out.println("running to csv method");
      }
      else if(toJson){
        System.out.println("running to JSON method");
      }else if (toAvro){
        System.out.println("running to avro method");
      }else {
        System.out.println("default method");
      }
} catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }
}

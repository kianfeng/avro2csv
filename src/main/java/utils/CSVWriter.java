package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class CSVWriter {
  /**
   * The class logger
   */
  private static final Logger LOGGER = Logger.getLogger(CSVWriter.class);

  /**
   * Convert the given List of String keys-values as a CSV String.
   *
   * @param flatJson   The List of key-value pairs generated from the JSON String
   *
   * @return The generated CSV string
   */
  public static String getCSV(List<Map<String, String>> flatJson, int idx) {
    // Use the default separator
    return getCSV(flatJson, ",", idx);
  }

  /**
   * Convert the given List of String keys-values as a CSV String.
   *
   * @param flatJson   The List of key-value pairs generated from the JSON String
   * @param separator  The separator can be: ',', ';' or '\t'
   *
   * @return The generated CSV string
   */
  public static String getCSV(List<Map<String, String>> flatJson, String separator, int idx) {
    String csvString;
    Set<String> headers = collectHeaders(flatJson);
    csvString = StringUtils.join(headers.toArray(), separator) + "\n";

    for (Map<String, String> map : flatJson) {
      if (idx != 0) csvString = "";
      csvString = csvString + getSeperatedColumns(headers, map, separator);
    }



    return csvString;
  }

  /**
   * Write the given CSV string to the given file.
   *
   * @param csvString  The csv string to write into the file
   * @param fileName   The file to write (included the path)
   */
  public static void writeToFile(String csvString, String fileName) {
    try {
      FileUtils.write(new File(fileName), csvString);
    } catch (IOException e) {
      LOGGER.error("CSVWriter#writeToFile(csvString, fileName) IOException: ", e);
    }
  }

  /**
   * Write the given CSV from a flat json to the given file.
   *
   * @param flatJson
   * @param separator
   * @param fileName
   * @param headers
   */
  public static void writeLargeFile(List<Map<String, String>> flatJson, String separator, String fileName, Set<String> headers){
    String csvString;
    csvString = StringUtils.join(headers.toArray(), separator) + "\n";
    File file = new File(fileName);

    try {
      // ISO8859_1 char code to Latin alphabet
      FileUtils.write(file, csvString, "ISO8859_1");

      for (Map<String, String> map : flatJson) {
        csvString = "";
        csvString = getSeperatedColumns(headers, map, separator) + "\n";
        Files.write(Paths.get(fileName), csvString.getBytes("ISO8859_1"), StandardOpenOption.APPEND);
      }
    } catch (IOException e) {
      LOGGER.error("CSVWriter#writeLargeFile(flatJson, separator, fileName, headers) IOException: ", e);
    }
  }

  /**
   * Get separated comlumns used a separator (comma, semi column, tab).
   *
   * @param headers The CSV headers
   * @param map     Map of key-value pairs contains the header and the value
   *
   * @return a string composed of columns separated by a specific separator.
   */
  private static String getSeperatedColumns(Set<String> headers, Map<String, String> map, String separator) {
    List<String> items = new ArrayList<String>();
    for (String header : headers) {
      String value = map.get(header) == null ? "" : map.get(header).replaceAll("[\\,\\;\\r\\n\\t\\s]+", " ");
      items.add(value);
    }

    return StringUtils.join(items.toArray(), separator);
  }

  /**
   * Get the CSV header.
   *
   * @param flatJson
   *
   * @return a Set of headers
   */
  private static Set<String> collectHeaders(List<Map<String, String>> flatJson) {
    Set<String> headers = new LinkedHashSet<String>();

    for (Map<String, String> map : flatJson) {
      headers.addAll(map.keySet());
    }

    return headers;
  }

  /**
   * Get the CSV ordered header
   *
   * @param flatJson
   *
   * @return a Set of ordered headers
   */
  public static Set<String> collectOrderedHeaders(List<Map<String, String>> flatJson) {
    Set<String> headers = new TreeSet<String>();
    for (Map<String, String> map : flatJson) {
      headers.addAll(map.keySet());
    }
    return headers;
  }
}
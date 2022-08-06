import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AvroToCSV_V3 {
  /**
   * Retrieves contents from Avro file
   *
   * Contains the following steps:
   *  1. Reads avro file
   *  2. Parses Schema to retrieve field names
   *  3. Loops through input to find values with 'Body' field
   *  4. Converts contents of Body (ByteBuffer type) to String
   *  5. Converts String to Contents POJO and retrieves contents
   *
   * @param file
   * @return
   */

}

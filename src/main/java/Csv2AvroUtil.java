//import org.apache.avro.Schema;
//import org.apache.avro.generic.GenericData;
//import org.apache.avro.generic.GenericDatumWriter;
//import org.apache.avro.generic.GenericRecord;
//import org.apache.avro.io.Encoder;
//import org.apache.avro.io.EncoderFactory;
//import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.nio.ByteBuffer;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//
//public class Csv2AvroUtil {
//  private Schema schema = null;
//  private String split = ",";
//  private final int bufferSize = 5 * 1024 * 1024;
//  private int batchSize = 10000;
//  private String topic;
//
//
//  /***
//   * initialize
//   * */
//  public Csv2AvroUtil(String schemaUrl, String topic, String split){
////    try {
////      this.topic = topic;
////      Schema.Parser parser = new Schema.Parser();
//////      CachedSchemaRegistryClient cachedSchemaRegistryClient = new CachedSchemaRegistryClient(schemaUrl, 100);
//////      schema = parser.parse(cachedSchemaRegistryClient.getLatestSchemaMetadata(topic).getSchema());
//////      System.out.println("get schema successfully, schema: " + schema.toString());
//////      this.split = split;
//////    } catch( IOException e ) {
//////      System.out.println("get schema failed!");
//////      e.printStackTrace();
//////    } catch( RestClientException e ) {
//////      System.out.println("get schema failed!");
//////      e.printStackTrace();
//////    }
//}
//
//  /***
//   *convert csv to avro
//   * */
//  public byte[] convert(List<String> lines){
//    ByteArrayOutputStream out = new ByteArrayOutputStream(bufferSize);
//    GenericDatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(schema);
//    Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
//
//    try{
//      for(String line: lines){
//        System.out.println("line:" + line);
//        String[] vals = line.split(this.split);
//        GenericRecord record = new GenericData.Record(this.schema);
//        if(vals.length != schema.getFields().size()) {
//          continue;
//        }
//        for(int i = 0;i < schema.getFields().size();++i){
//          Object o = convert(schema.getFields().get(i).schema(), vals[i]);
//          record.put(i, o);
//        }
//        writer.write(record, encoder);
//      }
//      encoder.flush();
//      out.flush();
//    } catch( IOException e ) {
//      e.printStackTrace();
//    } catch( Exception e ) {
//      e.printStackTrace();
//    }
//
//    return out.toByteArray();
//  }
//
//  private Object convert(Schema schema, String v) throws Exception {
//    Object o = null;
//    if (null == v) {
//      return null;
//    }
//
//    if (v.length() == 0 || v.equals("")) {
//      switch (schema.getType()) {
//        case STRING:
//          return v;
//        case BYTES:
//          return ByteBuffer.wrap(v.getBytes("UTF-8"));
//        case UNION:
//          break;
//        default:
//          return null;
//      }
//    }
//
//    switch (schema.getType()) {
//      case NULL:
//        o = null;
//        break;
//      case BOOLEAN:
//        o = Boolean.parseBoolean(v);
//        break;
//      case INT:
//        o = Integer.parseInt(v);
//        break;
//      case LONG:
//        o = Long.parseLong(v);
//        break;
//      case FLOAT:
//        o = Float.parseFloat(v);
//        break;
//      case DOUBLE:
//        o = Double.parseDouble(v);
//        break;
//      case BYTES:
//        try {
//          o = ByteBuffer.wrap(v.getBytes("UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//          e.printStackTrace();
//        }
//        break;
//      case STRING:
//        o = v;
//        break;
//      case UNION:
//        for (Schema mem : schema.getTypes()) {
//          o = convert(mem, v);
//          // break when encounter not null value, or we will get null
//          if (o != null) break;
//        }
//        break;
//      case RECORD:
//        throw new Exception("Unsopported test.avro type:" + RECORD);
//      case MAP:
//        throw new Exception("Unsopported test.avro type:" + RECORD);
//      case ENUM:throw new Exception("Unsopported test.avro type:" + RECORD);
//      case ARRAY:
//        throw new Exception("Unsopported test.avro type:" + RECORD);
//      case FIXED:
//        throw new Exception("Unsopported test.avro type:" + RECORD);
//      default:
//        throw new Exception("Unsopported test.avro type:" + RECORD);
//    }
//    return o;
//  }
//
//  public void readFile(String file, String toPath) {
//    try {
//      FileMgr mgr = new FileMgr(this.topic, toPath);
//      BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//      String line;
//      List<String> lines = new ArrayList<String>(this.batchSize);
//      int size = 0;
//      while ((line = in.readLine()) != null) {
//        if(0 == size) lines.clear();
//        lines.add(line);
//        ++size;
//        if(size >= batchSize){
//          byte[] data = this.convert(lines);
//          mgr.append(data);
//          lines.clear();
//          size = 0;
//        }
//      }
//      if(size>0 && !lines.isEmpty()){
//        byte[] data = this.convert(lines);
//        mgr.append(data);
//      }
//      in.close();
//      mgr.close();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//
//  public static void main(String[] args){
//    if (args.length != 5) {
//      return;
//    }
//    String schema = args[0];
//    String topic = args[1];
//    String split = args[2];
//    String file = args[3];
//    String toPath = args[4];
//    Csv2AvroUtil c2a = new Csv2AvroUtil(schema, topic, split);
//    c2a.readFile(file, toPath);
//
//  }
//}
//

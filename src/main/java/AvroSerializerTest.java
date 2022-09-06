//import com.avro.example.MyUser;
//import java.io.File;
//import java.io.IOException;
//
//import org.apache.avro.Schema;
//import org.apache.avro.file.DataFileWriter;
//import org.apache.avro.generic.GenericRecord;
//import org.apache.avro.io.DatumWriter;
//import org.apache.avro.specific.SpecificDatumWriter;
//
//
//public class AvroSerializerTest {
//
//  public static void MyCommandLine(String[] args) throws IOException {
//
//    MyUser myUser1 = new MyUser();
//    myUser1.setName("Tom");
//    myUser1.setFavoriteNumber(7);
//
//    MyUser myUser2 = new MyUser("Jack", 15, "red");
//
//    MyUser myUser3 = MyUser.newBuilder()
//        .setName("Harry")
//        .setFavoriteNumber(1)
//        .setFavoriteColor("green")
//        .build();
//
////    DatumWriter<MyUser> userDatumWriter = new SpecificDatumWriter<>(MyUser.class);
////    DataFileWriter<MyUser> dataFileWriter = new DataFileWriter<MyUser>(userDatumWriter);
////    dataFileWriter.create(myUser1.getSchema(), new File("./src/MyCommandLine/resources/users.avro"));
////    dataFileWriter.append(myUser1);
////    dataFileWriter.append(myUser2);
////    dataFileWriter.append(myUser3);
////    dataFileWriter.close();
//    File file = new File("twitter.avsc");
//    Schema schema = new org.apache.avro.Schema.Parser().parse(file);
//    DatumWriter<GenericRecord> userDatumWriter = new SpecificDatumWriter<>(schema);
//    DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(userDatumWriter);
//    dataFileWriter.create(schema, new File("./src/MyCommandLine/resources/users2.avro"));
//    dataFileWriter.append(myUser1);
//    dataFileWriter.append(myUser2);
//    dataFileWriter.append(myUser3);
//    dataFileWriter.close();
//
//  }
//
//}
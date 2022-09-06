import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class myCommandLineTest {
  @Test
  void csv2avro(){
    MyCommandLine myCommandLine = new MyCommandLine();
    myCommandLine.main(new String[]{"-a src/main/folder1/test1.csv"});
    myCommandLine.main(new String[]{"-c test2.avro"});


    myCommandLine.main(new String[]{"-a test2.sb"});
  }

}
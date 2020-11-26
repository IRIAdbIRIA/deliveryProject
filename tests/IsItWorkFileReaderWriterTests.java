import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsItWorkFileReaderWriterTests {

    @Test
    public void readTest1(){
        FileReaderWriter fileReaderWriter = new FileReaderWriter(new Data());
        int result = fileReaderWriter.readFile("intestfile1");
        Assertions.assertEquals(0,result);
    }

    @Test
    public void readTest2(){
        FileReaderWriter fileReaderWriter = new FileReaderWriter(new Data());
        int result = fileReaderWriter.readFile("intestfile2");
        Assertions.assertEquals(1,result);
    }

    @Test
    public void readTest3(){
        FileReaderWriter fileReaderWriter = new FileReaderWriter(new Data());
        int result = fileReaderWriter.readFile("intestfile3");
        Assertions.assertEquals(1,result);
    }

    @Test
    public void readTest4(){
        FileReaderWriter fileReaderWriter = new FileReaderWriter(new Data());
        int result = fileReaderWriter.readFile("intestfile4");
        Assertions.assertEquals(1,result);
    }

    @Test
    public void readTest5(){
        FileReaderWriter fileReaderWriter = new FileReaderWriter(new Data());
        int result = fileReaderWriter.readFile("intestfile5");
        Assertions.assertEquals(1,result);
    }

    @Test
    public void readTest6(){
        FileReaderWriter fileReaderWriter = new FileReaderWriter(new Data());
        int result = fileReaderWriter.readFile("intestfile6");
        Assertions.assertEquals(1,result);
    }

    @Test
    public void readTest7(){
        FileReaderWriter fileReaderWriter = new FileReaderWriter(new Data());
        int result = fileReaderWriter.readFile("intestfile7");
        Assertions.assertEquals(1,result);
    }

    @Test
    public void readTest8(){
        FileReaderWriter fileReaderWriter = new FileReaderWriter(new Data());
        int result = fileReaderWriter.readFile("intestfile8");
        Assertions.assertEquals(1,result);
    }

    @Test
    public void readTest9(){
        FileReaderWriter fileReaderWriter = new FileReaderWriter(new Data());
        int result = fileReaderWriter.readFile("intestfile9");
        Assertions.assertEquals(1,result);
    }

    @Test
    public void readTest10(){
        FileReaderWriter fileReaderWriter = new FileReaderWriter(new Data());
        int result = fileReaderWriter.readFile("intestfile10");
        Assertions.assertEquals(1,result);
    }
}

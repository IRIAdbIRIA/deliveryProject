import java.util.Scanner;

public class Main {

    public static void main(String[] argv){

        Data data = new Data();
        FileReaderWriter fileReaderWriter = new FileReaderWriter(data);

        if (fileReaderWriter.readFile(argv[0]) == 1){
            return;
        }

        Algorithm algorithm = new Algorithm(fileReaderWriter.data);
        algorithm.start();

        TimerForWriting timerForWriting;
        if (argv.length > 1){
            timerForWriting = new TimerForWriting(algorithm,500,argv[1],data);
        }else {
            timerForWriting = new TimerForWriting(algorithm,500,"out",data);
        }
        timerForWriting.start();
        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Wpisz '0' żeby skończyć działanie programu");
            String tmp = scanner.next();
            if (tmp.equals("0")){

                timerForWriting.work = false;
                algorithm.work = false;
                break;
            }
        }
    }

}

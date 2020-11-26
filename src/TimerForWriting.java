public class TimerForWriting extends Thread{

    private final Algorithm algorithm;
    private final int delay;
    private final String filename;
    private final Data data;

    boolean work = true;

    TimerForWriting(Algorithm algorithm, int delay, String fileName, Data data){
        this.algorithm = algorithm;
        this.delay = delay;
        this.filename = fileName;
        this.data = data;
    }

    @Override
    public void run(){

        FileReaderWriter fileReaderWriter = new FileReaderWriter(data);

        try {
            while (work) {

                sleep(delay);
                fileReaderWriter.writeFile(filename + ".txt", algorithm.bestIndividual);

            }
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }
}

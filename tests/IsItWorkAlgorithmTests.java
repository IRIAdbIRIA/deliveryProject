import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsItWorkAlgorithmTests {

    @Test
    public void firstGenerationTest(){
        int result = 1;
        int length;
        Data data = new Data();
        FileReaderWriter fileReaderWriter = new FileReaderWriter(data);
        fileReaderWriter.readFile("intestfile1");
        AlgorithmForTests algorithm = new AlgorithmForTests(data);
        algorithm.createFirstGeneration();
        if (algorithm.prevGeneration.size() != 1000){
            result = 0;
            Assertions.assertEquals(1,result);
        }
        length = algorithm.prevGeneration.get(0).length();
        for (int i = 0;i < algorithm.prevGeneration.size();i++){
            int procent = 0;
            char[] tmpCharArray = new char[4];

            if (algorithm.prevGeneration.get(i).length() != length){
                result = 0;
                Assertions.assertEquals(1,result);
            }

            for (int k = 0;k < data.pharmacies.size();k++){
                for (int d = 0;d < data.producers.size();d++){
                    System.arraycopy(algorithm.prevGeneration.get(i).toCharArray(), k * 4 * data.producers.size() + d * 4, tmpCharArray, 0, 4);
                    if (String.valueOf(tmpCharArray).equals("1.00")){
                        procent += 100;
                    } else if (String.valueOf(tmpCharArray).equals("0.00")){
                        procent += 0;
                    } else {
                        char[] tmp2 = new char[2];
                        tmp2[0] = tmpCharArray[2];
                        tmp2[1] = tmpCharArray[3];
                        procent += Integer.parseInt(String.valueOf(tmp2));
                    }
                }
                if (procent != 100){
                    System.out.println(procent);
                    result = 0;
                    Assertions.assertEquals(1,result);
                }
                procent = 0;
            }
        }

        Assertions.assertEquals(1,result);
    }

    @Test
    public void selectionTest(){
        int result = 1;
        long minimalCost;
        Data data = new Data();
        FileReaderWriter fileReaderWriter = new FileReaderWriter(data);
        fileReaderWriter.readFile("intestfile1");
        AlgorithmForTests algorithm = new AlgorithmForTests(data);
        algorithm.createFirstGeneration();
        algorithm.selection();
        for (int i = 0;i < 100;i++){
            if (algorithm.best20Costs[0] != 0){
                break;
            }
            algorithm.prevGeneration = null;
            algorithm.createFirstGeneration();
            algorithm.selection();
            if (i == 99){
                result = 0;
                Assertions.assertEquals(1,result);
            }
        }
        minimalCost = algorithm.bestIndividualCost;
        for (int i = 0;i < algorithm.best20Costs.length;i++){
            if (algorithm.best20Costs[i] < minimalCost){
                if (algorithm.best20Costs[i] == 0){
                    break;
                }
                result = 0;
                Assertions.assertEquals(1,result);
            }
        }
        Assertions.assertEquals(1,result);
    }

    @Test
    public void crossingTest(){
        int result = 1;
        AlgorithmForTests algorithm = new AlgorithmForTests(new Data());
        algorithm.data.pharmacies.add(new Pharmacy());
        algorithm.data.pharmacies.add(new Pharmacy());
        algorithm.data.pharmacies.add(new Pharmacy());
        algorithm.data.producers.add(new Producer());
        algorithm.data.producers.add(new Producer());
        algorithm.data.producers.add(new Producer());
        String tmp1 = "0.200.400.400.350.350.300.500.500.00";
        String tmp2 = "0.100.600.300.500.200.300.050.550.40";
        algorithm.prevGeneration.add(tmp1);
        algorithm.prevGeneration.add(tmp2);
        algorithm.best20Costs[0] = 1;
        algorithm.best20Costs[1] = 1;
        algorithm.best20Ids[0] = 0;
        algorithm.best20Ids[1] = 1;
        algorithm.crossing();

        char[] tmpCharArray1 = new char[12];
        char[] tmpCharArray2 = new char[12];
        char[] tmpCharArray3 = new char[12];
        for (int i = 0;i < algorithm.prevGeneration.size();i++){
            for (int d = 0;d < 3;d++){
                System.arraycopy(tmp1.toCharArray(),d * 12,tmpCharArray1,0,12);
                System.arraycopy(tmp2.toCharArray(),d * 12,tmpCharArray2,0,12);
                System.arraycopy(algorithm.prevGeneration.get(i).toCharArray(),d * 12,tmpCharArray3,0,12);
                if (!String.valueOf(tmpCharArray1).equals(String.valueOf(tmpCharArray3)) && !String.valueOf(tmpCharArray2).equals(String.valueOf(tmpCharArray3))){
                    result = 0;
                    Assertions.assertEquals(1,result);
                }
            }
        }
        Assertions.assertEquals(1,result);
    }

    @Test
    public void mutatingTest(){
        int result = 1;
        int length;
        Data data = new Data();
        FileReaderWriter fileReaderWriter = new FileReaderWriter(data);
        fileReaderWriter.readFile("intestfile1");
        AlgorithmForTests algorithm = new AlgorithmForTests(data);
        algorithm.createFirstGeneration();
        algorithm.selection();
        algorithm.crossing();
        algorithm.mutating();

        if (algorithm.prevGeneration.size() != 1000){
            result = 0;
            Assertions.assertEquals(1,result);
        }
        length = algorithm.prevGeneration.get(0).length();
        for (int i = 0;i < algorithm.prevGeneration.size();i++){
            int procent = 0;
            char[] tmpCharArray = new char[4];

            if (algorithm.prevGeneration.get(i).length() != length){
                result = 0;
                Assertions.assertEquals(1,result);
            }

            for (int k = 0;k < data.pharmacies.size();k++){
                for (int d = 0;d < data.producers.size();d++){
                    System.arraycopy(algorithm.prevGeneration.get(i).toCharArray(), k * 4 * data.producers.size() + d * 4, tmpCharArray, 0, 4);
                    if (String.valueOf(tmpCharArray).equals("1.00")){
                        procent += 100;
                    } else if (String.valueOf(tmpCharArray).equals("0.00")){
                        procent += 0;
                    } else {
                        char[] tmp2 = new char[2];
                        tmp2[0] = tmpCharArray[2];
                        tmp2[1] = tmpCharArray[3];
                        procent += Integer.parseInt(String.valueOf(tmp2));
                    }
                }
                if (procent != 100){
                    System.out.println(procent);
                    result = 0;
                    Assertions.assertEquals(1,result);
                }
                procent = 0;
            }
        }

        Assertions.assertEquals(1,result);
    }
}

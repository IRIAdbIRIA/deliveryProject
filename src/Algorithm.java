import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Algorithm extends Thread {

    private final Data data;
    private List < String > prevGeneration = new ArrayList < >();
    private List < String > nextGeneration = new ArrayList < >();
    private long[] best20Costs = new long[20];
    private int[] best20Ids = new int[20];

    private long bestIndividualCost = 100000000;

    public boolean work = true;
    public String bestIndividual = null;

    Algorithm(Data data) {
        this.data = data;
    }

    @Override
    public void run() {

        while (true) {
            createFirstGeneration();
            selection();
            if (best20Costs[0] != 0) {

                crossing();
                mutating();
                break;

            }
        }

        while (work) {
            selection();
            crossing();
            mutating();
        }
    }

    private void createFirstGeneration() {

        Random random = new Random();
        int procent = 100;
        int tmp;

        for (int d = 0; d < 1000; d++) {
            String individual = "";
            for (int i = 0; i < data.pharmacies.size(); i++) {
                for (int k = 0; k < data.producers.size() - 1; k++) {

                    boolean isConnectionExist = false;
                    for (int l = 0; l < data.connections.size(); l++) {
                        if (data.connections.get(l).pharmacyId == data.pharmacies.get(i).id && data.connections.get(l).producerId == data.producers.get(k).id) {

                            tmp = random.nextInt(procent + 1);
                            procent -= tmp;
                            isConnectionExist = true;
                            if (tmp == 100){
                                individual = individual.concat("1.00");
                            }else if (tmp == 0){
                                individual = individual.concat("0.00");
                            }else if (tmp % 10 == 0) {
                                individual = individual.concat("0." + tmp);
                            }else if (tmp < 10) {
                                individual = individual.concat("0.0" + tmp);
                            }else{
                                individual = individual.concat("0." + tmp);
                            }
                            break;
                        }
                    }

                    if (!isConnectionExist) {
                        individual = individual.concat("0.00");
                    }
                }

                if (procent == 100){
                    individual = individual.concat("1.00");
                }else if (procent == 0){
                    individual = individual.concat("0.00");
                }else if (procent % 10 == 0) {
                    individual = individual.concat("0." + procent);
                }else if (procent < 10){
                    individual = individual.concat("0.0" + procent);
                }else {
                    individual = individual.concat("0." + procent);
                }
                procent = 100;
            }
            prevGeneration.add(individual);
        }
    }

    private void selection() {

        int pointerForBest20 = 0;

        ifImpossibleAnswerLink: for (int i = 0; i < 1000; i++) {

            char[] tmp = prevGeneration.get(i).toCharArray();
            int[] dailyMaxRemain = new int[data.producers.size()];
            int pointerForCharArray = 0;
            long costOfIndividual = 0;

            for (int k = 0; k < data.producers.size(); k++) {
                dailyMaxRemain[k] = data.producers.get(k).dailyProduction;
            }

            for (int k = 0; k < data.pharmacies.size(); k++) {
                for (int l = 0; l < data.producers.size(); l++) {
                    for (int n = 0; n < data.connections.size(); n++) {
                        if (data.connections.get(n).pharmacyId == k && data.connections.get(n).producerId == l) {

                            char[] tmpCharArray = new char[4];

                            System.arraycopy(tmp, pointerForCharArray, tmpCharArray, 0, 4);
                            if (data.pharmacies.get(k).dailyRequirement * Double.parseDouble(String.valueOf(tmpCharArray)) > data.connections.get(n).dailyMaximumNumber) {
                                continue ifImpossibleAnswerLink;
                            }

                            dailyMaxRemain[l] -= data.pharmacies.get(k).dailyRequirement * Double.parseDouble(String.valueOf(tmpCharArray));
                            if (dailyMaxRemain[l] < 0) {
                                continue ifImpossibleAnswerLink;
                            }

                            costOfIndividual += data.pharmacies.get(k).dailyRequirement * Double.parseDouble(String.valueOf(tmpCharArray)) * data.connections.get(n).cost;
                        }
                    }

                    pointerForCharArray += 4;
                }
            }

            if (pointerForBest20 < 20) {

                best20Costs[pointerForBest20] = costOfIndividual;
                best20Ids[pointerForBest20] = i;
                pointerForBest20++;

            } else {

                long max = best20Costs[0];
                int maxPointer = 0;

                for (int k = 1; k < 20; k++) {

                    if (best20Costs[k] > max) {
                        max = best20Costs[k];
                        maxPointer = k;
                    }

                    if (max > costOfIndividual) {

                        best20Costs[maxPointer] = costOfIndividual;
                        best20Ids[maxPointer] = i;
                    }
                }
            }
        }

        for (int i = 0; i < pointerForBest20; i++) {
            if (best20Costs[i] < bestIndividualCost) {
                bestIndividual = prevGeneration.get(best20Ids[i]);
                bestIndividualCost = best20Costs[i];
            }
        }
    }

    private void crossing() {

        List < String > afterCrossingGeneration = new ArrayList < >();
        int bestCount = 0;

        while (bestCount < 20 && best20Costs[bestCount] != 0) {
            bestCount++;
        }

        if (bestCount == 1) {

            String tmp = prevGeneration.get(best20Ids[0]);
            prevGeneration = null;

            for (int i = 0; i < 20; i++) {
                prevGeneration.add(tmp);
            }

            return;
        }

        for (int i = 0; i < 20; i++) {

            Random random = new Random();
            char[] tmpChar = new char[4 * data.producers.size()];
            int tmp1;
            int tmp2;
            String newIndividual = "";
            char[] prevIndividual1;
            char[] prevIndividual2;

            tmp1 = best20Ids[random.nextInt(bestCount)];
            tmp2 = best20Ids[random.nextInt(bestCount)];

            while (tmp1 == tmp2) {
                tmp2 = best20Ids[random.nextInt(bestCount)];
            }

            prevIndividual1 = prevGeneration.get(tmp1).toCharArray();
            prevIndividual2 = prevGeneration.get(tmp2).toCharArray();

            for (int k = 0; k < data.pharmacies.size(); k++) {

                if (random.nextInt(2) == 1) {
                    System.arraycopy(prevIndividual1, k * 4 * data.producers.size(), tmpChar, 0, 4 * data.producers.size());
                } else {
                    System.arraycopy(prevIndividual2, k * 4 * data.producers.size(), tmpChar, 0, 4 * data.producers.size());
                }

                newIndividual = newIndividual.concat(String.valueOf(tmpChar));
                tmpChar = new char[4 * data.producers.size()];
            }

            afterCrossingGeneration.add(newIndividual);
        }

        prevGeneration = afterCrossingGeneration;
    }

    private void mutating() {

        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            for (int k = 0; k < 50; k++) {

                String newIndividual = "";
                int tmp1;
                int tmp2;
                char[] tmpChar = new char[4];

                for (int d = 0; d < data.pharmacies.size(); d++) {

                    int residue = 100;
                    for (int s = 0; s < 4 * (data.producers.size() - 1); s += 4) {

                        System.arraycopy(prevGeneration.get(i).toCharArray(), d * 4 * data.producers.size() + s, tmpChar, 0, 4);
                        tmp1 = random.nextInt(21) - 10;
                        tmp2 = (int)(Double.parseDouble(String.valueOf(tmpChar)) * 100);

                        if (String.valueOf(tmpChar).equals("0.00")) {

                            boolean isConnection = false;
                            for (int con = 0; con < data.connections.size(); con++) {
                                if (data.connections.get(con).pharmacyId == data.pharmacies.get(d).id && data.connections.get(con).producerId == data.producers.get(s / 4).id) {
                                    isConnection = true;
                                }
                            }

                            if (!isConnection) {
                                newIndividual = newIndividual.concat("0.00");
                                continue;
                            }
                        }

                        if (residue < tmp1 + tmp2) {
                            if (residue % 10 == 0) {
                                newIndividual = newIndividual.concat((double) residue / 100 + "0");
                            } else {
                                newIndividual = newIndividual.concat(Double.toString((double) residue / 100));
                            }
                            residue = 0;
                        } else if (tmp2 + tmp1 <= 0) {
                            newIndividual = newIndividual.concat("0.00");
                        } else {
                            tmp2 = tmp2 + tmp1;
                            residue -= tmp2;
                            if (tmp2 % 10 == 0) {
                                newIndividual = newIndividual.concat((double) tmp2 / 100 + "0");
                            } else {
                                newIndividual = newIndividual.concat(Double.toString((double) tmp2 / 100));
                            }
                        }
                    }

                    if (residue % 10 == 0) {
                        newIndividual = newIndividual.concat((double) residue / 100 + "0");
                    } else {
                        newIndividual = newIndividual.concat(Double.toString((double) residue / 100));
                    }
                }

                nextGeneration.add(newIndividual);
            }
        }

        prevGeneration = nextGeneration;
        nextGeneration = new ArrayList < >();
    }
}
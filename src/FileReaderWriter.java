import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileReaderWriter {

    Data data;

    FileReaderWriter(Data data){
        this.data = data;
    }

    public int readFile(@NotNull String fileName){

        String tmp = null;
        Scanner scanner;
        int line = 1;

        try {

            File file = new File(fileName + ".txt");
            scanner = new Scanner(file);

        }catch (FileNotFoundException e){

            System.out.println("ERROR#1F(Nie znaleziono pliku konfiguracyjnego o podanej nazwie)");
            return 1;
        }
        try{
            while(!scanner.next().equals("#")){

                scanner.nextLine();
            }
            if (!scanner.hasNext()){

                System.out.println("ERROR#11NMS(nie znaleziono symbol ”#” przed tabela „Producenci szczepionek”. Proszę pamiętać, że symbol ”#” powinien znajdować się na początku linii)");
                return 1;
            }

            scanner.nextLine();
            while(true) {

                Producer producer = new Producer();

                if (line != 1){
                    try {

                        tmp = scanner.next();
                        producer.id = Integer.parseInt(tmp);

                    } catch (NumberFormatException e) {
                        break;
                    }
                } else {
                    try {

                        producer.id = Integer.parseInt(scanner.next());

                    } catch (NumberFormatException e) {

                        System.out.println("ERROR#1AI1(nieprzewidziane dane w kolumnie identyfikatorów tabeli „Producenci szczepionek” linia " + line + " )");
                        return 1;
                    }
                }

                scanner.next();
                producer.name = scanner.next();

                while (true){

                    tmp = scanner.next();
                    if (tmp.equals("|")){
                        break;
                    }else {
                        producer.name = producer.name.concat(" " + tmp);
                    }
                }

                try {

                    producer.dailyProduction = Integer.parseInt(scanner.next());

                } catch (NumberFormatException e) {

                    System.out.println("ERROR#1AI2(nieprzewidziane dane w kolumnie dziennej produkcjitabeli „Producenci szczepionek” linia " + line + " )");
                    return 1;
                }

                data.producers.add(producer);

                scanner.nextLine();
                line++;
            }
        }catch (NoSuchElementException e2) {

            System.out.println("ERROR#NSE(Nie znaleziono oczekiwanego elementu w linii " + line + " tabeli „Producenci szczepionek”)");
        }
        //----------------------------------------------------------------------------------------------------------------------------------------------------------

        line = 1;
        try {
            while (!tmp.equals("#")) {

                scanner.nextLine();
                tmp = scanner.next();
            }
        }catch (NoSuchElementException e){

            System.out.println("ERROR#12NMS(nie znaleziono symbol”#”pomiędzy tabelami „Producenci szczepionek”a „Apteki”. Proszę pamiętać, że symbol”#”powinien znajdować się na początku linii)");
            return 1;
        }

        scanner.nextLine();
        try {
            while(true) {

                Pharmacy pharmacy = new Pharmacy();

                if (line != 1){
                    try {

                        tmp = scanner.next();
                        pharmacy.id = Integer.parseInt(tmp);

                    } catch (NumberFormatException e) {
                        break;
                    }
                } else {
                    try {

                        pharmacy.id = Integer.parseInt(scanner.next());

                    } catch (NumberFormatException e) {

                        System.out.println("ERROR#2AI1(nieprzewidziane dane w kolumnie identyfikatorów tabeli „Apteki” linia " + line + " )");
                        return 1;
                    }
                }

                scanner.next();
                pharmacy.name = scanner.next();

                while (true){

                    tmp = scanner.next();
                    if (tmp.equals("|")){
                        break;
                    }else {
                        pharmacy.name = pharmacy.name.concat(" " + tmp);
                    }
                }

                try {

                    pharmacy.dailyRequirement = Integer.parseInt(scanner.next());

                } catch (NumberFormatException e) {

                    System.out.println("ERROR#2AI2(nieprzewidziane dane w kolumnie liczby dziennego zapotrzebowaniatabeli „Apteki” linia " + line + " )");
                    return 1;
                }

                data.pharmacies.add(pharmacy);
                scanner.nextLine();
                line++;
            }
        }catch (NoSuchElementException e2) {

            System.out.println("ERROR#NSE(Nie znaleziono oczekiwanego elementu w linii " + line + " tabeli „Apteki”)");
        }
        //----------------------------------------------------------------------------------------------------------------------------------------------------

        line = 1;
        try {
            while (!tmp.equals("#")) {

                scanner.nextLine();
                tmp = scanner.next();
            }
        }catch (NoSuchElementException e){

            System.out.println("ERROR#23NMS(nie znaleziono symbol”#”pomiędzy tabelami „Apteki”a„Połączenia producentów i aptek”. Proszę pamiętać, że symbol”#”powinien znajdować się na początku linii)");
            return 1;
        }
        scanner.nextLine();

        try {
            while(true) {

                if (!scanner.hasNext()){
                    break;
                }

                Connection connection = new Connection();

                if (line != 1){
                    try {

                        tmp = scanner.next();
                        connection.producerId = Integer.parseInt(tmp);

                    } catch (NumberFormatException e) {
                        break;
                    }
                } else {
                    try {

                        connection.producerId = Integer.parseInt(scanner.next());

                    } catch (NumberFormatException e) {

                        System.out.println("ERROR#3AI1(nieprzewidziane dane w kolumnie identyfikatorów producentówtabeli „Połączenia producentów i aptek” linia " + line + " )");
                        return 1;
                    }
                }

                scanner.next();
                try {

                    connection.pharmacyId = Integer.parseInt(scanner.next());

                } catch (NumberFormatException e) {

                    System.out.println("ERROR#3AI2(nieprzewidziane dane w kolumnie identyfikatorów aptektabeli „Połączenia producentów i aptek” linia " + line + " )");
                    return 1;
                }

                scanner.next();
                try {

                    connection.dailyMaximumNumber = Integer.parseInt(scanner.next());

                } catch (NumberFormatException e) {

                    System.out.println("ERROR#3AI3(nieprzewidziane dane w kolumnie dziennej maksymalnej liczby dostarczonych szczepionektabeli „Połączenia producentów i aptek” " + line + " )");
                    return 1;
                }

                scanner.next();
                try {

                    connection.cost = Double.parseDouble(scanner.next());

                } catch (NumberFormatException e) {

                    System.out.println("ERROR#3AI4(nieprzewidziane dane w kolumnie kosztu szczepionki tabeli „Połączenia producentów i aptek” " + line + " )");
                    return 1;
                }

                data.connections.add(connection);
                scanner.nextLine();
                line++;
            }
        }catch (NoSuchElementException e2) {

            System.out.println("ERROR#NSE(Nie znaleziono oczekiwanego elementu w linii " + line + " tabeli „Połączenia producentów i aptek”)");
        }
        return check();
    }

    public void writeFile(@NotNull String fileName, @NotNull String bestIndividual){

        FileWriter out;
        char[] tmpChar = new char[4];
        long totalCost = 0;
        try {

            out = new FileWriter(fileName);
            for (int i = 0;i < data.pharmacies.size();i++){
                for (int k = 0;k < data.producers.size();k++){

                    System.arraycopy(bestIndividual.toCharArray(), i * 4 * data.producers.size() + k * 4, tmpChar, 0, 4);
                    if (Double.parseDouble(String.valueOf(tmpChar)) != 0.00){

                        int connectionId = 0;
                        for (int con = 0;con < data.connections.size();con++){
                            if (data.connections.get(con).producerId == data.producers.get(k).id && data.connections.get(con).pharmacyId == data.pharmacies.get(i).id){
                                connectionId = con;
                                break;
                            }
                        }

                        totalCost += data.connections.get(connectionId).cost * data.pharmacies.get(i).dailyRequirement * Double.parseDouble(String.valueOf(tmpChar));
                        out.write(data.producers.get(k).name + " -> " + data.pharmacies.get(i).name + " [Koszt = " + (int)(Double.parseDouble(String.valueOf(tmpChar)) * data.pharmacies.get(i).dailyRequirement) +
                                " * " + data.connections.get(connectionId).cost + " = " + data.connections.get(connectionId).cost * data.pharmacies.get(i).dailyRequirement * Double.parseDouble(String.valueOf(tmpChar)) + "]");
                        out.append('\n');
                        out.flush();
                    }
                }
            }

            out.write("Opłaty całkowite: " + totalCost + " zł");
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int check(){

        for (int k = 0;k < data.producers.size() - 1;k++) {
            int tmp = data.producers.get(k).id;

            for (int i = k + 1; i < data.producers.size(); i++) {
                if (data.producers.get(i).id == tmp || data.producers.get(i).id < 0){

                    System.out.println("ERROR#1AC1(konflikt identyfikatorów w tabeli „Producenci szczepionek” linia " + i + " )");
                    return 1;
                }
            }
        }

        for (int k = 0;k < data.producers.size();k++) {

            int tmp = data.producers.get(k).dailyProduction;
            if (tmp < 0){

                System.out.println("ERROR#1AC2(konflikt liczby dziennej produkcji w tabeli „Producenci szczepionek” linia " + k + " )");
                return 1;
            }
        }
        //------------------------------------------------------------------------------------------------------------------------------------------------
        for (int k = 0;k < data.pharmacies.size() - 1;k++) {
            int tmp = data.pharmacies.get(k).id;

            for (int i = k + 1; i < data.pharmacies.size(); i++) {
                if (data.pharmacies.get(i).id == tmp || data.pharmacies.get(i).id < 0){

                    System.out.println("ERROR#2AC1(konflikt identyfikatorów w tabeli „Apteki” linia " + i + " )");
                    return 1;
                }
            }
        }

        for (int k = 0;k < data.pharmacies.size();k++) {
            int tmp = data.pharmacies.get(k).dailyRequirement;

            if (tmp < 0){

                System.out.println("ERROR#1AC2(konflikt liczby dziennego zapotrzebowania w tabeli „Apteki” linia " + k + " )");
                return 1;
            }
        }
        //------------------------------------------------------------------------------------------------------------------------------------------------
        for (int k = 0;k < data.connections.size();k++) {
            int tmp = data.connections.get(k).producerId;
            boolean exist = false;

            for (int i = 0; i < data.producers.size(); i++) {
                if (data.producers.get(i).id == tmp) {
                    exist = true;
                    break;
                }
            }
            if (!exist){

                System.out.println("ERROR#3AC1(konflikt identyfikatorów producentów w tabeli „Połączenia producentów i aptek” linia " + k + " )");
                return 1;
            }
        }

        for (int k = 0;k < data.connections.size();k++) {

            int tmp = data.connections.get(k).pharmacyId;
            boolean exist = false;

            for (int i = 0; i < data.pharmacies.size(); i++) {
                if (data.pharmacies.get(i).id == tmp) {
                    exist = true;
                    break;
                }
            }
            if (!exist){

                System.out.println("ERROR#3AC1(konflikt identyfikatorów aptek w tabeli „Połączenia producentów i aptek” linia " + k + " )");
                return 1;
            }
        }

        for (int i = 0;i < data.pharmacies.size();i++){
            for (int k = 0;k < data.producers.size();k++){
                boolean exist = false;
                for (int c = 0;c < data.connections.size();c++){
                    if (data.connections.get(c).pharmacyId == data.pharmacies.get(i).id && data.connections.get(c).producerId == data.producers.get(k).id){
                        exist = true;
                    }
                }

                if (!exist){

                    Connection connection = new Connection();
                    connection.producerId = data.producers.get(k).id;
                    connection.pharmacyId = data.pharmacies.get(k).id;
                    connection.cost = 1;
                    connection.dailyMaximumNumber = 0;

                    data.connections.add(connection);
                }
            }
        }

        for (int k = 0;k < data.connections.size();k++) {
            int tmp = data.connections.get(k).dailyMaximumNumber;
            if (tmp < 0){

                System.out.println("ERROR#3AI3(nieprzewidziane dane w kolumnie dziennej maksymalnej liczby dostarczonych szczepionektabeli „Połączenia producentów i aptek” linia " + k + " )");
                return 1;
            }
        }

        for (int k = 0;k < data.connections.size();k++) {
            double tmp = data.connections.get(k).cost;
            if (tmp < 0){

                System.out.println("ERROR#3AI4(nieprzewidziane dane w kolumnie kosztu szczepionki tabeli „Połączenia producentów i aptek” linia " + k + " )");
                return 1;
            }
        }

        return 0;
    }
}

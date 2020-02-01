import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static String movementFile = "data/movementList.csv";
    private static ArrayList<AccountOperation> operations = new ArrayList<>();

    public static void main(String[] args) {
        operations = loadOperationsFromFile();
        System.out.println("Сумма Расходов состовляет: " + AccountOperation.costSum(operations));
        System.out.println("Сумма Доходов состовляет: " + AccountOperation.incomeSum(operations) + "\n" );
        AccountOperation.printMap(AccountOperation.getMapOfTrasactions(operations));
    }
    private static ArrayList<AccountOperation> loadOperationsFromFile()
    {
        try
        {
            CSVReader reader = new CSVReader(new FileReader(movementFile), ',', '"', 1);
            List<String[]> allRows = reader.readAll();
            for (String[] row : allRows){
                if(row.length != 8) {
                    System.out.println("Wrong line");
                    continue;
                }
                if(row[6].equals("0")) {
                    operations.add(new AccountOperation(
                            row[5].split("\\s+")[1],
                            OperationType.COST,
                            Float.valueOf(row[7].replaceAll(",", "."))
                            ));
                }else {
                    operations.add(new AccountOperation(
                            row[5].split("\\s+")[1],
                            OperationType.INCOME,
                            Integer.parseInt(row[6].replaceAll(",", "."))
                    ));
                }
            }
//            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(movementFile)));
//            for(int i = 1; i < lines.size(); i++)
//            {
//                String[] fragments = lines.get(i).split(",");
//                if(fragments.length != 8) {
//                    System.out.println("Wrong line: " + lines.get(i));
//                    continue;
//                }
//                if(fragments[6].equals("0")) {
//                    operations.add(new AccountOperation(
//                            fragments[5].split(" ")[1],
//                            OperationType.COST,
//                            Integer.parseInt(fragments[7])
//                            ));
//                }else {
//                    operations.add(new AccountOperation(
//                            fragments[5].split(" ")[1],
//                            OperationType.INCOME, Integer.parseInt(fragments[6])
//                    ));
//                }
//            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return operations;
    }
}

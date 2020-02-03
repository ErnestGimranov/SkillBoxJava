import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static String movementFile = "data/movementList.csv";
    private static String newMovementFile = "data/newMovementList.csv";
    private static ArrayList<AccountOperation> operations = new ArrayList<>();
    public static final String REGEX_SINGLE_REVERS_SLASH = "\\\\";
    public static final String REGEX_DOUBLE_REVERS_SLASH = "\\\\\\\\";
    private static final int MAX_LINE_LENGTH = 8;
    private static final int INDEX_COST = 7;
    private static final int INDEX_INCOME = 6;
    private static final int INDEX_DESCRIPTION = 5;
    private static final int INDEX_DESCRIPTION_IN_DESCRIPTION = 1;


    public static void main(String[] args) {
        operations = loadOperationsFromFile();
        AccountOperation.printCostSum(operations);
        AccountOperation.printIncomeSum(operations);
        System.out.println("------------------------");
        AccountOperation.printMap(AccountOperation.getMapOfTrasactions(operations));
    }
    private static ArrayList<AccountOperation> loadOperationsFromFile()
    {
        try
        {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(movementFile)));
            PrintWriter printWriter = new PrintWriter(newMovementFile);
            lines.forEach(line -> printWriter.write(line.replaceAll(REGEX_SINGLE_REVERS_SLASH, REGEX_DOUBLE_REVERS_SLASH) + "\n"));
            printWriter.flush();
            printWriter.close();
            CSVReader reader = new CSVReader(new FileReader(newMovementFile), ',', '"', 1);
            List<String[]> allRows = reader.readAll();
            for (String[] row : allRows){
                if(row.length != MAX_LINE_LENGTH) {
                    System.out.println("Wrong line");
                    continue;
                }
                if(Integer.parseInt(row[INDEX_INCOME]) == 0) {
                    operations.add(new AccountOperation(
                            row[INDEX_DESCRIPTION].split("\\s\\s\\s\\s+")[INDEX_DESCRIPTION_IN_DESCRIPTION].split(REGEX_SINGLE_REVERS_SLASH)[row[INDEX_DESCRIPTION].split("\\s\\s\\s\\s+")[INDEX_DESCRIPTION_IN_DESCRIPTION].split(REGEX_SINGLE_REVERS_SLASH).length - 1],
                            OperationType.COST,
                            Float.valueOf(row[INDEX_COST].replaceAll(",", ".")) * 100
                            ));
                }else {
                    operations.add(new AccountOperation(
                            row[INDEX_DESCRIPTION].split("\\s\\s\\s\\s+")[INDEX_DESCRIPTION_IN_DESCRIPTION].split(REGEX_SINGLE_REVERS_SLASH)[row[INDEX_DESCRIPTION].split("\\s\\s\\s\\s+")[INDEX_DESCRIPTION_IN_DESCRIPTION].split(REGEX_SINGLE_REVERS_SLASH).length - 1],
                            OperationType.INCOME,
                            Float.valueOf(row[INDEX_INCOME].replaceAll(",", ".")) * 100
                    ));
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return operations;
    }
}

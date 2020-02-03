import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class AccountOperation {
    private String description;
    private Long transaction;
    private OperationType type;

    public AccountOperation(String description, OperationType type, Float transaction){
            this.transaction = transaction.longValue();
            this.description = description;
            this.type = type;
    }

    public static void printIncomeSum(ArrayList<AccountOperation> operationsList) {
        long sum = operationsList.stream().filter(o -> o.type == OperationType.INCOME).map(o -> o.transaction).reduce((t1, t2) -> t1 + t2).get();
        System.out.println("Сумма Приходов состовляет: " + sum / 100 + "." + sum % 100);
    }

    public static void printCostSum(ArrayList<AccountOperation> operationsList){
        long sum = operationsList.stream().filter(o -> o.type == OperationType.COST).map(o -> o.transaction).reduce((t1, t2) -> t1 + t2).get();
        System.out.println("Сумма Расходов состовляет: " + sum / 100 + "." + sum % 100);
    }

    public static TreeMap<String, Long> getMapOfTrasactions(ArrayList<AccountOperation> operations){
        TreeMap<String, Long> descrptionsMap = new TreeMap<>();
        for (AccountOperation operation : operations){
            if (!descrptionsMap.containsKey(operation.description) && operation.type == OperationType.COST){
                descrptionsMap.put(operation.description, operation.transaction);
            }else if(descrptionsMap.containsKey(operation.description) && operation.type == OperationType.COST){
                descrptionsMap.put(operation.description, descrptionsMap.get(operation.description) + operation.transaction);
            }
        }
        return descrptionsMap;
    }

    public static void printMap(Map<String, Long> map) {
        for(String key : map.keySet()){
            System.out.println("Descrption: " + key + " RUB: " + map.get(key) / 100 + "." + map.get(key) % 100);
        }
    }

}

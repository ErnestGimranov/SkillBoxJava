import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class AccountOperation {
    private String description;
    private float transaction;
    private OperationType type;

    public AccountOperation(String description, OperationType type, float transaction){
            this.transaction = transaction;
            this.description = description;
            this.type = type;
    }

    public static int incomeSum(ArrayList<AccountOperation> operationsList) {
        int sum = 0;
        for(AccountOperation operation : operationsList){
            if (operation.type == OperationType.INCOME){
                sum += operation.transaction;
            }
        }
        return sum;
    }

    public static int costSum(ArrayList<AccountOperation> operationsList){
        int sum = 0;
        for(AccountOperation operation : operationsList){
            if (operation.type == OperationType.COST){
                sum += operation.transaction;
            }
        }
        return sum;
    }

    public static TreeMap<String, Float> getMapOfTrasactions(ArrayList<AccountOperation> operations){
        TreeMap<String, Float> descrptionsMap = new TreeMap<>();
        for (AccountOperation operation : operations){
            if (!descrptionsMap.containsKey(operation.description) && operation.type == OperationType.COST){
                descrptionsMap.put(operation.description, operation.transaction);
            }else if(descrptionsMap.containsKey(operation.description) && operation.type == OperationType.COST){
                descrptionsMap.put(operation.description, descrptionsMap.get(operation.description) + operation.transaction);
            }
        }
        return descrptionsMap;
    }

    public static void printMap(Map<String, Float> map) {
        for(String key : map.keySet()){
            System.out.println("Descrption: " + key + " RUB: " + map.get(key));
        }
    }

}

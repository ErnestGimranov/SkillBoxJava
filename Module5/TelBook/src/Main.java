import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    private static final String  REGEX_NAME = "[a-zA-Z]+";
    private static final String  REGEX_TELEPHONE = "(^((\\+7|7|8)+([0-9]){10})$)";
    private static final String  REGEX_NOT_NUMBERS = "[^0-9]";
    private static TreeMap<String, String> telephoneList = new TreeMap<>();

    public static void checkTelephone(String someNumber){

        if(telephoneList.containsValue(someNumber)){
            printInfo(someNumber);
        }else{
            addByNumber(someNumber);
        }
    }

    public static void printInfo(String someString){
        if (someString.matches(REGEX_TELEPHONE)){
            getKeyFromValue(telephoneList, someString);
        }else{
            System.out.println("Name: " + someString + " Telephone: " + telephoneList.get(someString));
        }

    }

    public static void addByNumber(String someNumber){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите имя: ");
        telephoneList.put(in.nextLine(), someNumber);
    }

    public static void getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                System.out.println("Name: " + o + " Telephone: " + value);
            }
        }
    }

    public static void checkName(String someString){
        if(telephoneList.containsKey(someString)){
            printInfo(someString);
        }else{
            addByName(someString);
        }
    }

    public static void addByName(String someString){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите номер телефона: ");
        String num = in.nextLine().replaceAll(REGEX_NOT_NUMBERS, "");
        if(num.matches(REGEX_TELEPHONE)){
            telephoneList.put(someString, num);
        }else{
            System.out.println("Введен не верный формат!");
        }
    }

    public static void printMap(Map<String, String> map) {
        for(String key : map.keySet()){
            System.out.println("Name: " + key + " Telephone: " + map.get(key));
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Введите телефон или имя(eng)(List - чтобы напечатать всех).");
            String choise = in.nextLine();
            if (choise.equalsIgnoreCase("LIST")) {
                printMap(telephoneList);
            } else if(choise.equalsIgnoreCase("EXIT")) {
                break;
            } else if (choise.matches(REGEX_NAME)) {
                checkName(choise);
            }else if (choise.matches(REGEX_TELEPHONE)) {
                choise.replaceAll(REGEX_NOT_NUMBERS, "");
                checkTelephone(choise);
            }else {
                System.out.println("Неверная команда!");
            }

        }
    }
}

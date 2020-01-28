import java.util.*;

public class Main {
    public static ArrayList<String> coolNumbers = new ArrayList<>();
    public static HashSet<String> hSet = new HashSet<>();
    public static TreeSet<String> tSet = new TreeSet<>();

    public static void Generate(){
        String[] LETTERS = {"А","В","Е","К","М","Н","О","Р","С","Т","У","Х"};
        for (int i = 1; i <= 197; i++){
            for (int j = 1; j < 10; j++){
                for(int k = 0; k < 12; k++){
                    String number;
                    if (i < 10) {
                        number = LETTERS[k] + j + j + j + LETTERS[k] + LETTERS[k] + 0 + i;
                    }else{
                        number = LETTERS[k] + j + j + j + LETTERS[k] + LETTERS[k] + i;
                    }
                    coolNumbers.add(number);
                }
            }
        }
    }

    public static void BinarySearching(String someString, ArrayList<String> someList){
        long start = System.nanoTime();
        int result = Collections.binarySearch(someList, someString);
        long duration = System.nanoTime() - start;
        System.out.println("Время поиска(в нс) = " + duration);
        if(result > -1){
            System.out.println("Номер найден с помощью бинарного поиска");
        }else System.out.println("Номер не найден с помощью бинарного поиска");
    }

    public static void Contains(String someString){
        long start = System.nanoTime();
        boolean result = coolNumbers.contains(someString);
        long duration = System.nanoTime() - start;
        System.out.println("Время поиска(в нс) = " + duration);
        if(result){
            System.out.println("Номер найден с помощью прямого перебора");
        }else System.out.println("Номер не найден с помощью прямого перебора");
    }

    public static void HashSetSearching(String someString){
        long start = System.nanoTime();
        boolean result = hSet.contains(someString);
        long duration = System.nanoTime() - start;
        System.out.println("Время поиска(в нс) = " + duration);
        if(result){
            System.out.println("Номер найден с помощью HashSet");
        }else System.out.println("Номер не найден с помощью HashSet");
    }

    public static void TreeSetSearching(String someString){
        long start = System.nanoTime();
        boolean result = tSet.contains(someString);
        long duration = System.nanoTime() - start;
        System.out.println("Время поиска(в нс) = " + duration);
        if(result){
            System.out.println("Номер найден с помощью TreeSet");
        }else System.out.println("Номер не найден с помощью TreeSet");
    }

    public static void main(String[] args) {
        Generate();
        Collections.sort(coolNumbers);
        hSet.addAll(coolNumbers);
        tSet.addAll(coolNumbers);
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("Введите номер для поиска(использовать кириллицу, exit - чтобы выйти) ");
            String number = in.nextLine();
            if(number.equalsIgnoreCase("exit")){
                break;
            }
            Contains(number);
            BinarySearching(number, coolNumbers);
            HashSetSearching(number);
            TreeSetSearching(number);
        }

    }
}

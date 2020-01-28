import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String  REGEX_ADD = "ADD\\s+.+";
    private static final String  REGEX_ADD_NUM = "ADD\\s\\d+.+";
    private static final String  REGEX_EDIT = "EDIT\\s\\d+.+";
    private static final String  REGEX_DELETE = "DELETE\\s\\d+.+";
    private static ArrayList<String> todoList = new ArrayList<>();

    public static void add(String someString){
        String[] line = someString.split("\\s+", 2);
        todoList.add(line[1]);
    }

    public static void addNum(String someString){
        String[] line = someString.split("\\s+", 3);
        if ((Integer.parseInt(line[1]) - 1) <= todoList.size()) {
            todoList.add(Integer.parseInt(line[1]) - 1, line[2]);
        }else {
            System.out.println("Недопустимое значение");
        }
    }

    public static void edit(String someString){
        String[] line = someString.split("\\s+", 3);
        if ((Integer.parseInt(line[1]) - 1) < todoList.size()) {
            todoList.remove(Integer.parseInt(line[1]) - 1);
            todoList.add(Integer.parseInt(line[1]) - 1, line[2]);
        }else {
            System.out.println("Недопустимое значение");
        }
    }

    public static void delete(String someString){
        String[] line = someString.split("\\s+", 3);
        if ((Integer.parseInt(line[1]) - 1) < todoList.size()) {
            todoList.remove(Integer.parseInt(line[1]) - 1);
        }else {
            System.out.println("Недопустимое значение");
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Введите команду (help - для помощи)");
            String choise = in.nextLine();
            if (choise.equalsIgnoreCase("LIST")) {
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println((i + 1) + " - " + todoList.get(i));
                }
            } else if (choise.matches(REGEX_ADD_NUM)){
                addNum(choise);
            } else if (choise.matches(REGEX_ADD)) {
                add(choise);
            } else if (choise.matches(REGEX_EDIT)) {
                edit(choise);
            } else if (choise.matches(REGEX_DELETE)) {
                delete(choise);
            } else if(choise.equalsIgnoreCase("EXIT")){
                break;
            }else if(choise.equalsIgnoreCase("HELP")){
                System.out.println("Список команд:\n\tADD ?[number]? - добавление в список\n\tEDIT [number] - замена элемента\n\tDELETE [number] - удаление элемента\n\tLIST - вывод всех элементов\n\tEXIT - завершение");
            }else {
                    System.out.println("Неверная команда!");
            }

        }
    }
}

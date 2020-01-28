import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static final String  REGEX_ADD = "ADD\\s+.+";
    private static final String  REGEX_CORRECT = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)";
    private static HashSet<String> emailList = new HashSet<>();

    public static void add(String someString){
        String[] line = someString.split("\\s+", 2);
        if(emailList.contains(line[1])){
            System.out.println("Такой E-mail уже существует. Попробуйте другой.");
        }else if(line[1].matches(REGEX_CORRECT)){
            emailList.add(line[1]);
        }else{
            System.out.println("E-mail введен не верно!");
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Введите Команду.");
            String choise = in.nextLine();
            if (choise.equalsIgnoreCase("LIST")) {
                for (String email : emailList) {
                    System.out.println(email);
                }
            } else if (choise.matches(REGEX_ADD)) {
                add(choise);
            } else if(choise.equalsIgnoreCase("EXIT")){
                break;
            }else {
                System.out.println("Неверная команда!");
            }

        }
    }
}

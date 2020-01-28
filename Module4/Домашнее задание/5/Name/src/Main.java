import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        String[] allNames = name.split("\\s");

        if(allNames.length == 1){
            System.out.println("Фамилия " + allNames[0]);
        }else if(allNames.length == 2){
            System.out.println("Фамилия " + allNames[0]);
            System.out.println("Имя " + allNames[1]);
        }else if(allNames.length >= 3){
            System.out.println("Фамилия " + allNames[0]);
            System.out.println("Имя " + allNames[1]);
            System.out.println("Отчетсво " + allNames[2]);
        }
    }
}

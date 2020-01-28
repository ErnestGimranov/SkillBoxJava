import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String number = in.nextLine();
        number = number.replaceAll("[^0-9]", "");
        if(number.charAt(1) != '7'){
            number = "7" + number;
        }
        System.out.println(number);
    }
}

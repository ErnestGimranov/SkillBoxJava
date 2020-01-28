import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scaner = new Scanner(System.in);
        System.out.println("Введите кол-во ящиков");
        int countBoxes = scaner.nextInt();
        Scanner scaner1 = new Scanner(System.in);
        System.out.println("Введите вместимость контейнеров");
        int capacityContainer = scaner1.nextInt();
        Scanner scaner2 = new Scanner(System.in);
        System.out.println("Введите вместимость грузовиков");
        int capacityCar = scaner2.nextInt();
        int numberContainer = 1;
        int numberCar = 1;
        for (int i = 1; i <= countBoxes; i++){
            if (numberCar == 1){
                System.out.println("Грузовик1:");
                numberCar++;
            }else if ((i - 1) % (capacityContainer * capacityCar) == 0){
                System.out.println("Грузовик" + numberCar + ":");
                numberCar++;
            }
            if (numberContainer == 1){
                System.out.println("    Контейнер1:");
                numberContainer++;
            }else if((i - 1) % (capacityContainer) == 0){
                System.out.println("    Контейнер" + numberContainer + ":");
                numberContainer++;
            }
            System.out.println("        Ящик" + i + ";");
        }



    }
}

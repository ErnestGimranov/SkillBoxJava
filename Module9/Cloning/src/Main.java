import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        for(;;){
            System.out.println("Введите путь откуда копировать: ");
            String pathToSource = in.nextLine();
            System.out.println("Введите путь куда копировать: ");
            String pathToDest = in.nextLine();
            if (CopyDir.copyDir(pathToSource, pathToDest)) {
                System.out.println("Копирование завершено успешно!");
            }else{
                System.out.println("Ошибка копирования!");
            }
        }
    }
}

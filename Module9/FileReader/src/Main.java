import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        for (; ; ) {
            System.out.println("Введите путь к каталогу: ");
            String path = in.nextLine().trim();
            File folder = new File(path);
            if (folder.isDirectory()) {
                FileSizeReader fileSizeReader = new FileSizeReader(Paths.get(path));
                System.out.println("Размер каталога: " + fileSizeReader.getDirectorySize());
            } else {
                System.out.println("Указанный путь не каталог, либо неверно введен путь!");
            }
        }

    }
}

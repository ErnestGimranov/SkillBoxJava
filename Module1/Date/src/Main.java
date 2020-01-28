import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] Args){
        DateFormat format = new SimpleDateFormat("kk.mm dd/MM/yyyy");
        Date date = new Date();
        System.out.println(format.format(date));
    }
}
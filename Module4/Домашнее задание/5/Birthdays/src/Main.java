import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - EEEEEEEE");
        Calendar calendarBirthday = Calendar.getInstance();
        Calendar calendarToday = Calendar.getInstance();
        calendarBirthday.set(1998, Calendar.MARCH, 4);
        short yearCount = 0;
        while (calendarBirthday.before(calendarToday)){
            System.out.println(yearCount + " - " + dateFormat.format(calendarBirthday.getTime()));
            calendarBirthday.add(Calendar.YEAR, 1);
            yearCount++;
        }
    }
}

import com.skillbox.airport.*;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args){
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        Date nowDate = new Date();
        Date lateDate = new Date();
        hourFormat.format(lateDate);
        hourFormat.format(nowDate);
        lateDate.setTime(nowDate.getTime() + 7200000);
//        System.out.println(hourFormat.format(lateDate) + " " + hourFormat.format(nowDate));

        Airport newAirport = Airport.getInstance();
        newAirport.getTerminals().stream()
                .map(terminal -> terminal.getFlights()).flatMap(flights -> flights.stream()).filter(flight -> flight.getType() == Flight.Type.DEPARTURE && flight.getDate().after(nowDate) && flight.getDate().before(lateDate))
                 .sorted(Comparator.comparing(flight -> flight.getDate()))
                .forEach(System.out::println);
    }
}
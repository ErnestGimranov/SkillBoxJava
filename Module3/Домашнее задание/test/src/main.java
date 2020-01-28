import com.skillbox.airport.*;


public class main {
    public static void main(String[] args) {
        Airport firstAirport = Airport.getInstance();
        System.out.println(firstAirport.getAllAircrafts() + "\n");
        System.out.println(firstAirport.getTerminals() + "\n");
        System.out.println("Кол-во самолетов = " + firstAirport.getAllAircrafts().size());
    }
}

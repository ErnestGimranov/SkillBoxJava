import AllTypesOfClients.Entity;
import AllTypesOfClients.Entrepreneur;
import AllTypesOfClients.Individual;

public class Main {
    public static void main(String[] args) {
        Individual firstClient = new Individual();
        Entity secondClient = new Entity();
        Entrepreneur thirdClient = new Entrepreneur();

        firstClient.upScore(5000);
        firstClient.downScore(300);
        secondClient.upScore(5000);
        secondClient.downScore(300);
        thirdClient.upScore(5000);
        thirdClient.downScore(300);
    }
}

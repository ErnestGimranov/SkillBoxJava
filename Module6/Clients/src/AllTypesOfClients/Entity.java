package AllTypesOfClients;

public class Entity extends Individual {
    public void downScore(double someMoney){
        someMoney *= 1.1;
        super.downScore(someMoney);
    }
}

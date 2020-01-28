package AllTypesOfClients;

public class Entrepreneur extends Individual {
    public void upScore(double someMoney){
        if (someMoney < 1000){
            someMoney *= 0.9;
        }else{
            someMoney *= 0.95;
        }
    super.upScore(someMoney);
    }
}

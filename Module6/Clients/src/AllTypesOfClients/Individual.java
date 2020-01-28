package AllTypesOfClients;

public class Individual extends Client {
    public void upScore(double someMoney){
        if(someMoney < 0){
            System.out.println("Операция невозможна.");
        }else{
            score+= someMoney;
            System.out.println("На счет № " + accountNumber + " зачислено: " + someMoney);
        }
        checkScore();
    }
    public void downScore(double someMoney){
        if (someMoney <= score){
            score-= someMoney;
            System.out.println("Со счета № " + accountNumber + " списано: " + someMoney);
        }else{
            System.out.println("Недостаточно средств.");
        }
        checkScore();
    }
}

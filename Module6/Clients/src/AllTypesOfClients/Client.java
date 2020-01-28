package AllTypesOfClients;

public abstract class Client {
    private static int accountCount = 1;
    int accountNumber;
    protected Client(){
        accountNumber = accountCount;
        accountCount++;
    }
    protected double score = 0;
    abstract void upScore(double someMoney);
    abstract void downScore(double someMoney);
    public void checkScore(){
        System.out.println("Остаток на счете № " + accountNumber + " составляет: " + score);
    }
}

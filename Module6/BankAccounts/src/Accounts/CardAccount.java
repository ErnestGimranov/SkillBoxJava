package Accounts;

public class CardAccount extends CheckingAccount{

    public void downScore(double count){
        count *= 1.01;
        super.downScore(count);
    }
}

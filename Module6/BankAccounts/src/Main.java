import Accounts.CardAccount;
import Accounts.CheckingAccount;
import Accounts.DepositeAccount;



public class Main {
    public static void main(String[] args){
        CheckingAccount someChechingAccount = new CheckingAccount();
        someChechingAccount.upScore(10000);
        someChechingAccount.downScore(1000);
        System.out.println();

        DepositeAccount someDepositeAccount = new DepositeAccount();
        someDepositeAccount.upScore(20000);
        someDepositeAccount.downScore(100);
        someDepositeAccount.getScore();
        System.out.println();

        CardAccount someCardAccount = new CardAccount();
        someCardAccount.upScore(30000);
        someCardAccount.downScore(5000);


    }
}

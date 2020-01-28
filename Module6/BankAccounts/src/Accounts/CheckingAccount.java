package Accounts;

public class CheckingAccount {
    protected double score;

    public CheckingAccount(){
        System.out.println("Счет открыт.");
        score = 0;
    }

    public void downScore(double count){
        if (score > count){
            score -= count;
            System.out.println("С вашего счета списано " + count + " рублей.");
            getScore();
        }else {
            System.out.println("Недостаточно средств.");
        }
    }

    public void upScore(double count){
        score += count;
        System.out.println("На ваш счет поступило " + count + " рублей.");
    }

    public void getScore() {
        System.out.println("На вашем счете " + score + " рублей.");
    }
}

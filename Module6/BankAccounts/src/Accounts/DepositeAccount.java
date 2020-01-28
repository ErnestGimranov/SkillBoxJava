package Accounts;

import java.util.Calendar;

public class DepositeAccount extends CheckingAccount {

    private Calendar calendarForUpScore = Calendar.getInstance();

    public void downScore(double count){
        Calendar nowCalendar = Calendar.getInstance();
        if(nowCalendar.after(calendarForUpScore) || nowCalendar.equals(calendarForUpScore)) {
            super.downScore(count);
        }else{
            System.out.println("Вы не можете сейчас снять деньги, последние внесение было менее месяца назад.");
        }
    }

    public void upScore(double count){
        calendarForUpScore = Calendar.getInstance();
        calendarForUpScore.add(Calendar.MONTH, 1);
        super.upScore(count);
    }
}

import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        int countAll = 30;
        DecimalFormat formatter = new DecimalFormat("#0.00°C");
        final float MIN = 32;
        final float MAX = 40;
        float[] temp = new float[countAll];
        float sum = 0;
        float countNorm = 0;
        for(int i = 0; i < temp.length; i++){
            temp[i] = (float) Math.round( (MIN + (MAX - MIN) * Math.random()) * 10) / 10;
            sum += temp[i];
            if(temp[i] < 36.9 && temp[i] > 36.2){
                countNorm++;
            }
        }
        System.out.println("Средняя температура = " + formatter.format(sum / countAll) + "\nКол-во здоровых - " + countNorm);


    }
}

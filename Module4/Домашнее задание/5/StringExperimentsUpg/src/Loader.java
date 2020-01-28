
public class Loader
{
    public static void main(String[] args)
    {
        String text = "Вася заработал 5000 рублей, Петя - 7000 рубля, а Маша - 30000 рублей, зарплата Ивана - 15000";
        int sum = 0;
        String[] sentence = text.split("\\,\\s");
        for(int i = 0; i < sentence.length; i++){
            sum += Integer.parseInt(sentence[i].replaceAll("\\D+",""));
        }
        System.out.println("Суммарная зарплата = " + sum);

    }
}
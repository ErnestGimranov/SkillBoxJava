public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count += 7843;
        System.out.println(container.count);
        System.out.println("Сумма цифр = " + sumDigits(container.count));

    }

    public static Integer sumDigits(Integer number)
    {
        int sum = 0;
        for (int i = 0; i < number.toString().length(); i++){
            Character symbol = number.toString().charAt(i);
            sum += Character.getNumericValue(symbol);
        }
        return sum;
    }
}

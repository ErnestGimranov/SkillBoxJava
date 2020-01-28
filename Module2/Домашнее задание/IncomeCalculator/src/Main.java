import java.util.Scanner;

public class Main
{
    private static int minIncome = 200000;
    private static int maxIncome = 900000;

    private static int officeRentCharge = 140000;
    private static int telephonyCharge = 12000;
    private static int internetAccessCharge = 7200;

    private static int assistantSalary = 45000;
    private static int financeManagerSalary = 90000;

    private static double mainTaxPercent = 0.24;
    private static double managerPercent = 0.15;

    private static double minInvestmentsAmount = 100000;        //Описание переменных

    public static void main(String[] args)
    {
        while(true)
        {
            System.out.println("Введите сумму доходов компании за месяц " +
                "(от 200 до 900 тысяч рублей): ");
            int income = (new Scanner(System.in)).nextInt();

            if(!checkIncomeRange(income)) {         //Проверка на соответствие интервалу допустимых значений
                continue;
            }

            double managerSalary = income * managerPercent;     //Рассчет з/п мэнеджера
            double pureIncome = income - managerSalary -        //Рассчет дохода учитывая затраты
                calculateFixedCharges();
            double taxAmount = mainTaxPercent * pureIncome;     //Рассчет налогов
            double pureIncomeAfterTax = pureIncome - taxAmount;     //Вычетание налога из остатка бюджета

            boolean canMakeInvestments = pureIncomeAfterTax >=      //Проверка на возможность инвестирования
                minInvestmentsAmount;

            var minIncomeInvest = (minInvestmentsAmount + calculateFixedCharges() - mainTaxPercent * calculateFixedCharges())/(1 - managerPercent - mainTaxPercent + managerPercent * mainTaxPercent);
            System.out.println("Начиная с этой суммы дохода, компания может инвестировать:" + minIncomeInvest);     //Рассчет точки безубыточности

            System.out.println("Зарплата менеджера: " + managerSalary); //Вывод з/п мэнеджера
            System.out.println("Общая сумма налогов: " + // Вывод налогов если есть
                (taxAmount > 0 ? taxAmount : 0));
            System.out.println("Компания может инвестировать: " +
                (canMakeInvestments ? "да" : "нет"));       // Вывод возможности инвестирования
            if(pureIncome < 0) {
                System.out.println("Бюджет в минусе! Нужно срочно зарабатывать!");      //Если бюджет после оплаты расходов меньше нуля вывод совета)
            }
        }
    }

    private static boolean checkIncomeRange(int income)     //Проверка границ
    {
        if(income < minIncome)
        {
            System.out.println("Доход меньше нижней границы");
            return false;
        }
        if(income > maxIncome)
        {
            System.out.println("Доход выше верхней границы");
            return false;
        }
        return true;
    }

    private static int calculateFixedCharges()      //Рассходы
    {
        return officeRentCharge +
                telephonyCharge +
                internetAccessCharge +
                assistantSalary +
                financeManagerSalary;
    }
}

package employees;

public class TopManager extends EmployeesClass{

    static final double INCOME_FOR_PRIZE = 10000000;
    private double fixed;
    private double prize = 10000;


    public TopManager(double fixed){
        this.fixed = fixed;
        salary = 0;

    }

    @Override
    public double getMonthSalary(double companyIncome) {
        if(companyIncome > INCOME_FOR_PRIZE){
            salary = fixed + prize;
            return salary;
        }else{
            salary = fixed;
            return salary;
        }
    }

}

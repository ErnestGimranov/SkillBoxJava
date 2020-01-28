package employees;

public class SalesManager extends EmployeesClass{

    private double fixed;
    private double earned;

    public SalesManager(double fixed, double earned){
        this.fixed = fixed;
        this.earned = earned;
    }

    @Override
    public double getMonthSalary(double companyIncome) {
            salary = earned * 0.05 + fixed;
            return salary;
    }

}

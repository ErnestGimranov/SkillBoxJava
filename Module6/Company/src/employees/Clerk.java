package employees;

public class Clerk extends EmployeesClass{


    public Clerk(double fixed){
        salary = fixed;
    }

    @Override
    public double getMonthSalary(double companyIncome) {
        return salary;
    }



}

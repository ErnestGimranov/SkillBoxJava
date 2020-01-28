import employees.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Company {
    private double income;
    private static ArrayList<EmployeesClass> employeesArray = new ArrayList<>();
    DecimalFormat formatter = new DecimalFormat("#0.00");

    public Company(double companyIncome){
        income = companyIncome;
    }

    public int getEmployeesArrayLength(){
        return employeesArray.size();
    }

    public void getEmployeesMonthSalary(int index){
        employeesArray.get(index).getMonthSalary(income);
    }

    public double getIncome() {
        return income;
    }

    public void addWroker(EmployeesClass employee){
        employee.toActive();
        employeesArray.add(employee);
    }


    public void deleteWorker(int number){
        employeesArray.get(number).toInactive();
        employeesArray.remove(number);

    }
    private void printSalary(ArrayList<EmployeesClass> employeesArray, int count){
        int j = 1;
        for (int i = 0; i < count; i++) {
            if (employeesArray.get(i).getStatus() == Status.ACTIVE) {
                System.out.println(j + ") " + employeesArray.get(i) + " Salary = " + formatter.format(employeesArray.get(i).getMonthSalary(income)));
                j++;
            }else {
                continue;
            }
        }
    }

    public void getLowestSalaryStaff(int count){
        if (count <= employeesArray.size()) {
            Collections.sort(employeesArray);
            System.out.println("Lowest Salary:");
            printSalary(employeesArray, count);
        }else{
            System.out.println("Кол-во сотрудников меньше введенного числа");
        }
    }
    public void getTopSalaryStaff(int count){
        if (count <= employeesArray.size()) {
            Collections.sort(employeesArray, Collections.reverseOrder());
            System.out.println("Top Salary:");
            printSalary(employeesArray, count);
        }else{
            System.out.println("Кол-во сотрудников меньше введенного числа");
        }
    }
}

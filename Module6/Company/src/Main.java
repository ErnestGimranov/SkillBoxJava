import employees.*;


public class Main {
    public final static double COMPANY_INCOME = 10000000;
    public static void main(String[] args) {
        Company someCompany = new Company(COMPANY_INCOME);
        for(int i = 0; i < 270; i++) {
            if(i < 90) {
                double fixed = Math.random() * (25000 - 20000) + 20000;
                someCompany.addWroker(new Clerk(fixed));
            }else if(i < 180){
                double earned = Math.random() * (100000 - 50000) + 50000;
                double fixed = Math.random() * (35000 - 25000) + 25000;
                someCompany.addWroker(new SalesManager(fixed, earned));
            }else{
                double fixed = Math.random() * (35000 - 25000) + 25000;
                someCompany.addWroker(new TopManager(fixed));
            }
        }
        for (int i = 0; i < someCompany.getEmployeesArrayLength(); i++){
            someCompany.getEmployeesMonthSalary(i);
        }
        someCompany.getLowestSalaryStaff(10);
        System.out.println();
        someCompany.getTopSalaryStaff(10);
    }
}

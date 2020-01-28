package employees;

public abstract class EmployeesClass implements Employees,Comparable<EmployeesClass> {
    private Status status;
    protected double salary;

    @Override
    public void toActive() {
        status = Status.ACTIVE;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public void toInactive() {
        status = Status.INACTIVE;
    }

    @Override
    public int compareTo(EmployeesClass o) {
        if(salary > o.salary){
            return 1;
        }
        if(salary < o.salary){
            return -1;
        }
        return 0;
    }
}

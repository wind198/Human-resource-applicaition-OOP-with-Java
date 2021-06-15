/*
 * This class represent employee in company
 * It has following additional attribute:
 * overtimeHours
 * */
class Employee extends Staff {
    //Attribute
    private int overtimeHours;

    //Contructor
    public Employee(int id, String name, int age, double salaryFactor, String joiningTime, Department department, int vacationDays, int overtimeHours) {
        super(id, name, age, salaryFactor, joiningTime, department, vacationDays);
        this.overtimeHours = overtimeHours;
        this.calculateSalary();
    }

    //Additional Getter and Setter
    public void setOvertimeHours(int overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public int getOvertimeHours() {
        return overtimeHours;
    }

    //This method calculate employee salary
    @Override
    public void calculateSalary() {
        this.salary = this.salaryFactor * 3000000. + overtimeHours * 200000;
    }

    //Method to display staff information
    public void displayInformation() {
        super.displayInformation();
        System.out.println("7. Overtime hours: " + getOvertimeHours() + "\t");
        System.out.printf("8. Salary: %8.0f%n ", getSalary());
    }
}

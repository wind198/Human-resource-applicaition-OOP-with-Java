/*
 * This class represent employee in company
 * It has following additional attribute:
 * overtimeHours
 * */
class Manager extends Staff {
    //Attribute
    private String title;
    private double bonus;

    //Contructor
    public Manager(int id, String name, int age, double salaryFactor, String joiningTime, Department department, int vacationDays, String title, double bonus) {
        super(id, name, age, salaryFactor, joiningTime, department, vacationDays);
        this.title = title;
        this.bonus = bonus;
        this.calculateSalary();
    }

    //Additional Getter and Setter
    public double getBonus() {
        return bonus;
    }

    public String getTitle() {
        return title;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //This method calculate employee salary
    @Override
    public void calculateSalary() {
        this.salary = this.salaryFactor * 5000000. + bonus;
    }

    //Method to display staff information
    public void displayInformation() {
        super.displayInformation();
        System.out.println("7. Title: " + getTitle() + "\t");
        System.out.println("8. Bonus: " + getBonus() + "\t");
        System.out.printf("9. Salary: %8.0f%n ", getSalary());
    }
}

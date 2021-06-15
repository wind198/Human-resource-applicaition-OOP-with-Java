import java.io.Serializable;

/*
 * This class represent staff in company
 * It is abstract
 * It implement Icalculator interface, which calculate the salary of staff
 * It implement Comparable interface, so we can compare different staff base on salary
 * It implement Serializable interface, so we can write its subclass's instance to database
 * It has following attribute:
 * id
 * name
 * age
 * salary factor
 * joining time
 * department
 * vacation days
 * salary
 * All of them can be set directly
 * Except for salary, which is calculated from other attributes
 * */
abstract class Staff implements ICalculator, Comparable, Serializable {
    //attributes
    protected int id;
    protected String name;
    protected int age;
    protected double salaryFactor;
    protected String joiningTime;
    protected Department department;
    protected int vacationDays;
    protected double salary;

    //
    //Constructor
    public Staff(int id, String name, int age, double salaryFactor, String joiningTime, Department department, int vacationDays) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salaryFactor = salaryFactor;
        this.joiningTime = joiningTime;
        this.department = department;
        this.vacationDays = vacationDays;
    }

    //
    //Getter and Setter
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public double getSalaryFactor() {
        return salaryFactor;
    }

    public String getJoiningTime() {
        return joiningTime;
    }

    public Department getDepartment() {
        return department;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalaryFactor(double salaryFactor) {
        this.salaryFactor = salaryFactor;
    }

    public void setJoiningTime(String joiningTime) {
        this.joiningTime = joiningTime;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public double getSalary() {
        return salary;
    }
    //
    //Method to display staff information
    public void displayInformation() {
        System.out.print("0. Id: " + getId() + "\t");
        System.out.print("1. Name: " + getName() + "\t");
        System.out.print("2. Age: " + getAge() + "\t");
        System.out.print("3. Salary factor: " + getSalaryFactor() + "\t");
        System.out.print("4. Joining time: " + getJoiningTime() + "\t");
        System.out.print("5 .Department: " + getDepartment().getName() + "\t");
        System.out.print("6. Number of vacation days: " + getVacationDays() + "\t");
    }
    //
    // This method allow us to compare one staff with another base on salary
    @Override
    public int compareTo(Object temp) {
        Staff other = (Staff) temp;
        if (salary > other.salary) {
            return 1;
        } else if (salary < other.salary) {
            return -1;
        } else {
            return 0;
        }
    }
}

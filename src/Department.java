import java.io.Serializable;
/*
 * This class represent department in company
 * It implement Serializable interface, so we can write its instance to database
 * It has following attribute:
 * id
 * name
 * Number of Staff
 * Id and name can be set
 * But number of staff can't be set, it is updated automatically base on staff department information
 * */
class Department implements Serializable {
    private int id;
    private String name;
    private int staffNum;

//Constructor
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
        this.staffNum = 0;
    }

//Getter and Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStaffNum(int staffNum) {
        this.staffNum = staffNum;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStaffNum() {
        return staffNum;
    }

    //toString method help us to print the object with its info
    public String toString() {
        return ("1. Department id: " + this.id + "\t"
                + "2. Department name: " + this.name + "\t"
                + "3. Number of staff: " + this.staffNum + "\t");
    }
}

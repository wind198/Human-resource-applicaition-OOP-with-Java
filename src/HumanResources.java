import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/*
 * This class contain the main program of this Human resources software
 * When the program start, it will look into the 2 dat file in the project folder
 * which are named "staffDatabase.dat" & "departmentDatabase.dat"
 * These 2 files would be read into the program and stored in 2 arraylist, served as the database for the program
 * Then the Begin method would be called, which will display a list of function the program can provide
 * The user pick 1 function and the suitable method will start
 * After the function finish its job, the user will have the choices to continue or stop the program
 * If user choose stop, all the data of 2 arraylists will be written to the 2 dat files in the project folder
 * Then the program finish
 * */
public class HumanResources {
    public static void main(String[] args) {
        //Initialize 2 arraylist serve as database for the program
        ArrayList<Staff> staffDatabase = new ArrayList();
        ArrayList<Department> departmentDatabase = new ArrayList();
        //
        Scanner sc = new Scanner(System.in);//A scanner to read user input
        //
        //Read the 2 dat files in to database
        readDatabase(staffDatabase, departmentDatabase);
        //
        //Call the begin method
        begin(staffDatabase, departmentDatabase, sc);
        //User choose to stop the program
        //
        //Write data from 2 arraylists to 2 dat files
        writeToDatabase(staffDatabase, departmentDatabase);
        //
        //Finish writing data, the program finish
    }
/*
* This method read database files (.dat file in folder "data") to the program, stored in 2 arraylists
* It then print out indication about what information loaded to the program
* */
    public static void readDatabase(ArrayList<Staff> staffDatabase, ArrayList<Department> departmentDatabase) {
        ObjectInputStream ios = null;
        //Read department database
        int depReadCounter = 0;
        int staffReadCounter = 0;
        try {
            ios = new ObjectInputStream(new FileInputStream(".\\data\\departmentDatabase.dat"));
            while (true) {
                departmentDatabase.add((Department) ios.readObject());
                depReadCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //Read staff database;
        try {
            ios = new ObjectInputStream(new FileInputStream(".\\data\\staffDatabase.dat"));
            while (true) {
                staffDatabase.add((Staff) ios.readObject());
                staffReadCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Print out indication about what information loaded to the program
        System.out.println("Information of " + depReadCounter + " departments loaded from database.");
        System.out.println("Information of " + staffReadCounter + " staff loaded from database");
    }
    /*
     * This method write data of current session (stored in 2 arraylists) to database files (.dat files in folder "data")
     * It then print out indication about what information written to database
     * */
    public static void writeToDatabase(ArrayList<Staff> staffDatabase, ArrayList<Department> departmentDatabase) {
        ObjectOutputStream oos;
        int depWriteCounter = 0;
        int staffWriteCounter = 0;
        //Write department database;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(".\\data\\departmentDatabase.dat", false));
            for (Department aDep : departmentDatabase
            ) {
                oos.writeObject(aDep);
                depWriteCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Write staff database;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(".\\data\\staffDatabase.dat", false));
            for (Staff aStaff : staffDatabase
            ) {
                oos.writeObject(aStaff);
                staffWriteCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //print out indication about what information written to database
        System.out.println("Information of " + depWriteCounter + " departments written to database");
        System.out.println("Information of " + staffWriteCounter + " staff written to database");
    }

    /*
     * This method is called when the program begin, it show to user all the function that program provide
     * Then it ask user to pick 1 function
     * Depend on user input, it will call the corresponding method to process user request
     * */
    public static void begin(ArrayList<Staff> staffDatabase, ArrayList<Department> departmentDatabase, Scanner sc) {
        //This boolean show whether user want to continue to use the program
        boolean continuee = true;
        //This boolean show whether user input is correct
        boolean correctInput = true;
        System.out.println("Welcome to Human resource program");
        do {
            continuee = true;
            //Show the list all all function
            System.out.println("""
                    This program provide the following functionalities
                    Function 1. Add, delete or modify list of department in the company.
                    Function 2. Add,delete or update a staff in database.
                    Function 3. Find and display staff info by name or by Id.
                    Function 4: Display info of all staff
                    Function 5. Display list of department and its staff
                    Function 6: Display salary table of all staff 
                    Function 7: Display salary table of all staff by salary ascending order
                    Function 8: Display salary table of all staff by salary descending order
                    Function 9: Save changes to database
                    """);

            do {
                System.out.println("Which Function you want to choose? (type 1 to 8)");
                int answer = sc.nextInt();
                correctInput = true;
                //Start the block calling the corresponding methods for each function requested
                switch (answer) {
                    //case 1 correspond to add, delete or modify list of department
                    //Ask user to choose whether to add, delete or modify the list
                    case 1:
                        System.out.println("Type 1 to add new department\n" +
                                "Type 2 to delete an existing department\n" +
                                "Type 3 to update info of department");
                        answer = sc.nextInt();
                        do {
                            correctInput = true;
                            switch (answer) {
                                //If user type 1,call the method to add department
                                case 1:
                                    addDepartment(departmentDatabase, sc);
                                    break;
                                //If user type 2,call the method to delete department
                                case 2:
                                    deleteDepartment(departmentDatabase, sc);
                                    break;
                                //If user type 3,call the method to modify department
                                case 3:
                                    updateDepartment(departmentDatabase, sc);
                                    break;
                                //Other input mean wrong input
                                default:
                                    correctInput = false;
                                    System.out.println("Wrong input! Please choose again");
                            }
                        } while (!correctInput);
                        break;
                    //case 2 correspond to add, delete or modify list of staff
                    //Ask user to choose whether to add, delete or modify the list
                    case 2:
                        System.out.println("Type 1 to add new staff\n" +
                                "Type 2 to delete an existing staff\n" +
                                "Type 3 to update info of staff");
                        answer = sc.nextInt();
                        do {
                            correctInput = true;
                            switch (answer) {
                                //If user type 1,call the method to add staff
                                case 1:
                                    addStaff(staffDatabase, departmentDatabase, sc);
                                    break;
                                //If user type 2,call the method to delete staff
                                case 2:
                                    deleteStaff(staffDatabase, sc);
                                    break;
                                //If user type 3,call the method to modify staff info
                                case 3:
                                    updateStaffInfo(staffDatabase, departmentDatabase, sc);
                                    break;
                                //Other input mean wrong input
                                default:
                                    correctInput = false;
                                    System.out.println("Wrong input! Please choose again");
                            }
                        } while (!correctInput);
                        break;
                    //Case 3 correspond to find and display staff info by name or by Id
                    //Ask user to find staff by name or Id
                    case 3:
                        System.out.println("Type 1 to find by Id and\n" +
                                "Type 2 to find by Name");
                        answer = sc.nextInt();
                        do {
                            correctInput = true;
                            switch (answer) {
                                //If user type 1,call the method to find staff by Id
                                case 1:
                                    findStaffById(staffDatabase, sc);
                                    break;
                                //If user type 2,call the method to find staff by name
                                case 2:
                                    findStaffByName(staffDatabase, sc);
                                    break;
                                //Other input mean wrong input
                                default:
                                    correctInput = false;
                                    System.out.println("Wrong input! Please choose again");
                            }
                        } while (!correctInput);
                        break;
                    //Case 4 correspond to display info of all staff from database
                    case 4:
                        System.out.println("Below is information of all staff from database:");
                        displayAllStaff(staffDatabase);
                        break;
                    //Case 5 correspond to display list of department and its staff
                    case 5:
                        System.out.println("Below is information of all department and its staff from database:");
                        displayDepAndStaff(departmentDatabase, staffDatabase, sc);
                        break;
                    //Case 6 correspond to display salary info of all staff
                    case 6:
                        System.out.println("Below is salary info of all staff from database:");
                        displaySalaryTable(staffDatabase);
                        break;
                    //Case 7 correspond to display salary info of all staff in ascending order
                    case 7:
                        System.out.println("Below is salary info of all staff from database sorted in ascending order:");
                        displaySalaryTableInAscendingOrder(staffDatabase);
                        break;
                    //Case 8 correspond to display salary info of all staff in descending order
                    case 8:
                        System.out.println("Below is salary info of all staff from database sorted in descending order:");
                        displaySalaryTableInDescendingOrder(staffDatabase);
                        break;
                    case 9:
                        writeToDatabase(staffDatabase,departmentDatabase);
                        break;
                    default:
                        correctInput = false;
                        System.out.println("Wrong input, please type again.");
                }
            } while (!correctInput);
            //End the block calling the corresponding methods for each function requested
            //
            //Ask user whether to continue with other function or stop the program
            System.out.println("Do you want to continue? (type 1 to continue and 2 to stop");
            if (sc.nextInt() == 1) continuee = true;
            else {
                System.out.println("Thanks for using our program");
                continuee = false;
            }
        } while (continuee);
    }

    /*
     * This method add a new department to database
     * User will input Id and name of department respectively
     * The method will check if the Id is already take or not
     * The department then is added to database
     * */
    public static void addDepartment(ArrayList<Department> departmentDatabase, Scanner sc) {
        boolean correctInput;
        int departmentId;
        String departmentName;
        System.out.println("Please enter the following info for the new department:");
        //
        //Start asking for department Id
        //this loop ask and check if the Id already exist
        do {
            correctInput = true;
            System.out.println("Department Id number? ");
            departmentId = sc.nextInt();
            for (int i = 0; i < departmentDatabase.size(); i++) {
                if (departmentId == departmentDatabase.get(i).getId()) {
                    System.out.println("This Id already exist. Please use another one.");
                    correctInput = false;
                }
            }
        } while (!correctInput);
        //End asking for department Id
        //
        //Start asking for department name
        System.out.println("Department name? ");
        departmentName = sc.next();
        //End asking for department name
        //
        //Add the new department to database
        departmentDatabase.add(new Department(departmentId, departmentName));

    }

    /*
     * This method delete a department from database
     * User will input Id of department
     * The method will check if the if the department with that Id exist in database
     * The department if found is then deleted from database
     * */
    public static void deleteDepartment(ArrayList<Department> departmentDatabase, Scanner sc) {
        boolean correctInput;
        int departmentId;
        //This block ask Department id and check if that id exist
        do {
            correctInput = false;
            System.out.println("Id of department to delete? ");
            departmentId = sc.nextInt();
            for (Department department : departmentDatabase
            ) {
                //If the id exist, delete the department
                if (departmentId == department.getId()) {
                    departmentDatabase.remove(department);
                    System.out.println("Deletion completed.");
                    correctInput = true;
                    break;
                }
            }
            //Otherwise, ask user to re-enter the Id
            if (!correctInput) {
                System.out.println("Id number does not exist. Please re-enter: ");
            }
        } while (!correctInput);
    }

    /*
     * This method update info of a department
     * User will input Id of department
     * The method will check if the if the department with that Id exist in database
     * User then choose which info to update
     * The information is update which follow user input
     * */
    public static void updateDepartment(ArrayList<Department> departmentDatabase, Scanner sc) {
        boolean correctInput;
        int departmentfId;
        int whichInfoToChange;
        Department theDepartment = null;
        //This loop ask user to enter Id number
        do {
            correctInput = false;
            System.out.println("Please enter the Id of department you want to update info: ");
            departmentfId = sc.nextInt();
            //check if the Id exist
            for (int i = 0; i < departmentDatabase.size(); i++) {
                //If exist, display department info
                if (departmentfId == departmentDatabase.get(i).getId()) {
                    theDepartment = departmentDatabase.get(i);
                    correctInput = true;
                }
            }
            //Otherwise, ask user to re-enter the id
            if (!correctInput) System.out.println("Your id number is not found. Please type again.");
        } while (!correctInput);
        //
        //Ask user to choose which info to update
        do {
            correctInput = true;
            System.out.println("Below is list of info relating to this department");
            System.out.println(theDepartment);
            System.out.println("Please enter the number in the list above\n" +
                    "Correspond to the info you would like to change");
            whichInfoToChange = sc.nextInt();
            switch (whichInfoToChange) {
                //User choose 1 to update Id
                case 1:
                    System.out.println("You want to change Id number.\n" +
                            "Sorry! Our program currently doesn't support changing Id number one created.\n" +
                            "You can delete the department and recreate it with new Id number");
                    break;
                //User choose 2 to update Name
                case 2:
                    System.out.println("You want to change name.\n" +
                            "The current name is " + theDepartment.getName()
                            + "\nPlease enter new name.");
                    String newName = sc.next();
                    theDepartment.setName(newName);
                    System.out.println("Success! New name of department is " + theDepartment.getName());
                    break;
                //User choose 3 to update staff number
                case 3:
                    System.out.println("You want to change number of staff.\n" +
                            "The current number of staff is " + theDepartment.getStaffNum()
                            + "\nYou can't directly change staff number because it is set by staff info.");
                    //Other inputs mean wrong input
                default:
                    correctInput = false;
                    System.out.println("Your input is out of range!\n" +
                            "We can not proceed with this input");
            }
        } while (!correctInput);
    }

    /*
     * This method add a new staff to database
     * User will input various mandatory information of the staff
     * The method will check if the input is valid or not (Id existed, invalid joining time,...)
     * The staff then is added to database
     * Staff number of department is updated to adapt the new staff
     * */
    public static void addStaff(ArrayList<Staff> staffDatabase, ArrayList<Department> departmentDatabase, Scanner sc) {
        boolean correctInput = true;//This boolean controls different loop in this method
        //User can't add staff if there are no department set up in database
        if (departmentDatabase.size() == 0) {
            System.out.println("Sorry, there are no departments set up.\n" +
                    "You can not proceed to add staff because there are no department.");
        } else {
            System.out.println("Please type in the following information for the staff you want to add.");
            //
            //Start this section to ask whether this staff is employee or manager
            System.out.println("Is he/she a staff or a manager? (type 1 for staff and 2 for manager ");
            int employeeOrManager;
            //this integer stand if set to 1 indicate Employee, set to 2 indicate Manager
            //
            //This loop ask user whether the staff is employee or Manager
            do {
                correctInput = true;
                employeeOrManager = sc.nextInt();
                switch (employeeOrManager) {
                    case 1:
                    case 2:
                        break;
                    default:
                        System.out.println("Wrong input! Please answer again");
                        correctInput = false;//set input false to continue the loop
                }
            } while (!correctInput);
            //End this section to ask whether this staff is employee or manager
            //
            //Start the section asking for staff Id
            int staffId;
            //This loop ask for Id and check whether it is already exist
            do {
                correctInput = true;
                System.out.println("Staff Id? ");
                staffId = sc.nextInt();
                for (Staff staff : staffDatabase) {
                    if (staffId == staff.getId()) {
                        correctInput = false;
                        break;
                    }
                }
                if (!correctInput) System.out.println("This Id already exist. Please enter another Id");
            } while (!correctInput);
            //End the section asking for staff Id
            //
            //Start the section asking for staff name
            String staffName;
            System.out.println("Staff name? ");
            staffName = sc.next();
            //End the section asking for staff name
            //
            //Start the section asking for staff age
            int staffAge;
            System.out.println("Staff Age? ");
            staffAge = sc.nextInt();
            //End the section asking for staff age
            //
            //Start the section asking for salary facfor
            double salaryFactor;
            System.out.println("Salary Factor? ");
            salaryFactor = sc.nextDouble();
            //End the section asking for salary facfor
            //
            //Start the section asking for joining date
            String joiningDate, joiningMonth, joiningYear;
            String joiningTime;
            System.out.println("Please type in joining date in \"DD MM YYYY\" format");
            do {
                correctInput = true;
                joiningDate = sc.next();
                joiningMonth = sc.next();
                joiningYear = sc.next();
                joiningTime = joiningDate + "-" + joiningMonth + "-" + joiningYear;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                try {
                    LocalDate joiningTimeDate = LocalDate.parse(joiningTime, formatter);
                    System.out.println(joiningTimeDate);
                } catch (DateTimeParseException e) {
                    System.out.println("Wrong input, please enter joining time again.");
                    correctInput = false;
                }
            } while (!correctInput);
            //End the section asking for joining date
            //
            //Start the section asking for  department info
            Department staffDepartment;
            int departmentChoice;
            do {
                System.out.println("Please choose a department to which the staff belong to.\n"
                        + "Below is the list of all available departments\n" +
                        "Please enter a number correspond to the department you choose");
                correctInput = true;
                for (int i = 0; i < departmentDatabase.size(); i++) {
                    System.out.println((i + 1) + ". " + departmentDatabase.get(i).getName());
                }
                departmentChoice = sc.nextInt();
                if (departmentChoice > departmentDatabase.size() || departmentChoice < 1) {
                    System.out.println("Your number is out of range. Please type again.");
                    correctInput = false;
                }
            } while (!correctInput);
            staffDepartment = departmentDatabase.get(departmentChoice - 1);
            //End the section asking for department info
            //Start the section asking for number of vacation days
            int staffNumOfVacation;
            System.out.println("Staff number of vacation days? ");
            staffNumOfVacation = sc.nextInt();
            //End the section asking for number of vacation days
            //
            int overtimeHours = 0;
            String title = "";
            double bonus = 0;
            if (employeeOrManager == 1) {
                System.out.println("Staff overtime hours? ");
                overtimeHours = sc.nextInt();
            } else if (employeeOrManager == 2) {
                System.out.println("Staff Title? \n" +
                        "Type 1 for \"Business Leader\"\n" +
                        "Type 2 for \"Project Leader\"\n" +
                        "Type 3 for \"Technical Leader\"\n");

                do {
                    correctInput = true;
                    switch (sc.nextInt()) {
                        case 1:
                            title = "Business Leader";
                            bonus = 8000000.;
                            break;
                        case 2:
                            title = "Project Leader";
                            bonus = 5000000.;
                            break;
                        case 3:
                            title = "Technical Leader";
                            bonus = 6000000.;
                            break;
                        default:
                            System.out.println("Wrong input number, please enter again.");
                            correctInput = false;
                    }
                } while (!correctInput);
            }
            //Add new staff to database
            //Add employee
            if (employeeOrManager == 1)
                staffDatabase.add(new Employee(staffId, staffName, staffAge, salaryFactor, joiningTime, staffDepartment, staffNumOfVacation, overtimeHours));
                //Or add manager
            else if (employeeOrManager == 2)
                staffDatabase.add(new Manager(staffId, staffName, staffAge, salaryFactor, joiningTime, staffDepartment, staffNumOfVacation, title, bonus));
            //Update staff number of department database
            for (Department aDep : departmentDatabase
            ) {
                if (aDep.getId() == staffDepartment.getId()) {
                    aDep.setStaffNum(aDep.getStaffNum() + 1);
                    break;
                }
            }
        }
    }

    /*
     * This method delete a staff from database
     * User will input Id of the staff
     * The method will check if the if the staff with that Id exist in database
     * The staff if found is then deleted from database
     * */
    public static void deleteStaff(ArrayList<Staff> staffDatabase, Scanner sc) {
        boolean correctInput;
        int staffId;
        //check if the Id exist
        do {
            correctInput = false;
            System.out.println("Id of staff to delete? ");
            staffId = sc.nextInt();
            for (Staff staff : staffDatabase
            ) {
                //If exist, delete staff from database
                if (staffId == staff.getId()) {
                    staffDatabase.remove(staff);
                    System.out.println("Deletion completed.");
                    correctInput = true;
                    break;
                }
            }
            //Otherwise ask user to re-enter Id
            if (!correctInput) {
                System.out.println("Id number does not exist. Please re-enter: ");
            }
        } while (!correctInput);
    }

    /*
     * This method update info of a staff
     * User will input Id of staff
     * The method will check if the if the staff with that Id exist in database
     * User then choose which info to update
     * The information is update which follow user input
     * */
    public static void updateStaffInfo(ArrayList<Staff> staffDatabase, ArrayList<Department> departmentDatabase, Scanner sc) {
        boolean correctInput = true;
        int staffId;
        int whichInfoToChange;
        Staff theStaff;
        //Call the method "findStaffById", it help to check if the Id exist, if not exist
        //It ask user to re-enter the Id, or if user ignore the request, it return null
        // otherwise, it return the staff object with valid ID
        theStaff = findStaffById(staffDatabase, sc);
        if (theStaff != null) {
            do {
                correctInput = true;
                //Ask user which info to update
                System.out.println("Please enter the number in the list above\n" +
                        "Correspond to the info you would like to change");
                whichInfoToChange = sc.nextInt();
                switch (whichInfoToChange) {
                    //User want to update staff Id
                    case 0:
                        System.out.println("You want to change Id number.\n" +
                                "Sorry! Our program currently not support changing Id number one created.\n" +
                                "You can delete the staff and recreate it with new Id number");
                        break;
                    //User want to update staff Name
                    case 1:
                        System.out.println("You want to change name.\n" +
                                "The current name is " + theStaff.getName()
                                + "\nPlease enter new name.");
                        String newName = sc.next();
                        theStaff.setName(newName);
                        System.out.println("Success! New name of staff is " + theStaff.getName());
                        break;
                    //User want to update staff age
                    case 2:
                        System.out.println("You want to change age.\n" +
                                "The current name is " + theStaff.getAge()
                                + "\nPlease enter new age.");
                        int newAge = sc.nextInt();
                        theStaff.setAge(newAge);
                        System.out.println("Success! New age of staff is " + theStaff.getAge());
                        break;
                    //User want to update staff salary factor
                    case 3:
                        System.out.println("You want to change salary factor.\n" +
                                "The current salary factor is " + theStaff.getSalaryFactor()
                                + "\nPlease enter new salary factor.");
                        double newSalaryFactor = sc.nextDouble();
                        theStaff.setSalaryFactor(newSalaryFactor);
                        System.out.println("Success! New salary factor of staff is " + theStaff.getSalaryFactor());
                        break;
                    //User want to update staff joining time
                    case 4:
                        System.out.println("You want to change joining time.\n" +
                                "The current joining time is " + theStaff.getJoiningTime()
                        );
                        String joiningDate, joiningMonth, joiningYear;

                        System.out.println("Please type in new joining time in \"DD MM YYYY\" format");
                        //Check if the input is valid date data
                        do {
                            correctInput = true;
                            joiningDate = sc.next();
                            joiningMonth = sc.next();
                            joiningYear = sc.next();
                            String joiningTime = joiningDate + "-" + joiningMonth + "-" + joiningYear;
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            try {
                                LocalDate joiningTimeDate = LocalDate.parse(joiningTime, formatter);
                                System.out.println(joiningTimeDate);
                                theStaff.setJoiningTime(joiningTime);
                                //If input is not valid causing DateTimeParseException
                                //Ask user to re-enter data
                            } catch (DateTimeParseException e) {
                                System.out.println("Wrong input, please enter joining time again.");
                                correctInput = false;
                            }
                        } while (!correctInput);
                        System.out.println("Success! New joining time of staff is " + theStaff.getJoiningTime());
                        break;
                    //User want to update department
                    case 5:
                        System.out.println("You want to change department.\n" +
                                "The current department is " + theStaff.getDepartment()
                        );
                        do {
                            //List all department
                            System.out.println("Please choose the new department to which staff belong.\n"
                                    + "Below is the list of all available departments\n" +
                                    "Please enter a number correspond to the department you choose");
                            correctInput = true;
                            for (int i = 0; i < departmentDatabase.size(); i++) {
                                System.out.println((i + 1) + ". " + departmentDatabase.get(i));
                            }
                            //Ask user to pick new department
                            int department = sc.nextInt();
                            try {
                                theStaff.setDepartment(departmentDatabase.get(department - 1));
                                //If user input is out of range, causing IndexOutOfBoundsException
                                //Ask user to choose again
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Your number is out of range. Please type again.");
                                correctInput = false;
                            }
                        } while (!correctInput);
                        System.out.println("Success! New department of staff is " + theStaff.getDepartment().getName());
                        break;
                    //User want to update vacation days
                    case 6:
                        System.out.println("You want to change number of vacation days.\n" +
                                "The current vacation days is " + theStaff.getVacationDays() +
                                "\nPlease enter new number of vacation days."
                        );
                        int newNumVacationDay;
                        newNumVacationDay = sc.nextInt();
                        theStaff.setVacationDays(newNumVacationDay);
                        System.out.println("Success! New number of vacation days is " + theStaff.getVacationDays());
                        break;
                    //User want to update overtime hours for employee
                    //Or update title for manager
                    case 7:
                        //For employee
                        if (theStaff instanceof Employee) {
                            System.out.println("You want to change number of overtime hours.\n" +
                                    "The current overtime hours is " + ((Employee) theStaff).getOvertimeHours() +
                                    "\nPlease enter new number of overtime hours."
                            );
                            int newOvertimeHour;
                            newOvertimeHour = sc.nextInt();
                            ((Employee) theStaff).setOvertimeHours(newOvertimeHour);
                            System.out.println("Success! New number of overtime hours is " + ((Employee) theStaff).getOvertimeHours());
                        }
                        //For manager
                        else if (theStaff instanceof Manager) {
                            System.out.println("You want to change title.\n" +
                                    "The current title is " + ((Manager) theStaff).getTitle() +
                                    "\nPlease enter new title."
                            );
                            String newTitle;
                            newTitle = sc.next();
                            ((Manager) theStaff).setTitle(newTitle);
                            System.out.println("Success! New title is " + ((Manager) theStaff).getTitle());
                        }
                        break;
                    //User want to update salary for employee or bonus for manager
                    case 8:
                        //For employee
                        if (theStaff instanceof Employee)
                            System.out.println("Sorry! You can not change salary directly.\n" +
                                    "Salary is calculated based on salary factor.\n" +
                                    "You can change salary factor instead");
                            //For manager
                        else if (theStaff instanceof Manager)
                            System.out.println("Sorry! You can not change bonus directly.\n" +
                                    "Bonus is calculated based on title.\n" +
                                    "You can change title instead");
                        break;
                    //User want to update salary for manager
                    case 9:
                        if (theStaff instanceof Employee) System.out.println("Your input is out of range!\n" +
                                "We can not proceed with this input");
                        else if (theStaff instanceof Manager)
                            System.out.println("Sorry! You can not change salary directly.\n" +
                                    "Salary is calculated based on salary factor.\n" +
                                    "You can change salary factor instead");
                        break;
                    //Other inputs mean wrong input
                    default:
                        System.out.println("Your input is out of range!\n" +
                                "We can not proceed with this input");
                        correctInput = false;
                }

            } while (!correctInput);
        }
    }

    /*
     * This method find staff by Id
     * User will input Id of staff
     * The method will check if the if the staff with that Id exist in database
     * The information of the staff with valid Id will then be displayed
     * */
    public static Staff findStaffById(ArrayList<Staff> staffDatabase, Scanner sc) {
        boolean correctInput;
        int staffId;
        int answer;
        Staff theStaff = null;
        do {
            correctInput = false;
            //Ask user to enter Id number
            System.out.println("Please enter the Id of staff: ");
            staffId = sc.nextInt();
            for (int i = 0; i < staffDatabase.size(); i++) {
                //If id exist, display information
                if (staffId == staffDatabase.get(i).getId()) {
                    System.out.println("Below is list of info relating to this staff");
                    theStaff = staffDatabase.get(i);
                    theStaff.displayInformation();
                    correctInput = true;
                }
            }
            //Otherwise, ask user to re-enter Id or quit the method
            if (!correctInput) {
                System.out.println("Your id number is not found. Type 1 to re-enter the Id or Type 2 to quit.");
                answer = sc.nextInt();
                //If user quit, method return 0
                if (answer != 1) {
                    System.out.println("Quit!");
                    return null;
                }
            }
        } while (!correctInput);
        //Method return valid Id
        return theStaff;
    }

    /*
     * This method find staff by Name
     * User will input name of staff
     * The method will check if the if the staff with that Id exist in database
     * The information of the staff with valid name will then be displayed
     * */
    public static void findStaffByName(ArrayList<Staff> staffDatabase, Scanner sc) {
        boolean correctInput;
        String staffName;
        int answer;
        int j = 1;
        do {
            correctInput = false;
            //Ask user to enter staff name
            System.out.println("Please enter the name of staff: ");
            staffName = sc.next();
            for (int i = 0; i < staffDatabase.size(); i++) {
                //If a staff with this name is found, dispay information
                if (staffName.equals(staffDatabase.get(i).getName())) {
                    System.out.println("Info of staff number " + j + " with this name");
                    staffDatabase.get(i).displayInformation();
                    correctInput = true;
                    j++;
                }
            }
            //Otherwise, ask user to re-enter the name or quit the method
            if (!correctInput) {
                System.out.println("The name was not found. Type 1 to re-enter the name or Type 2 to quit.");
                answer = sc.nextInt();
                if (answer != 1) {
                    System.out.println("Quit!");
                    return;
                }
            }
        } while (!correctInput);
    }

    /*
     * This method display info of all staff in database
     * */
    public static void displayAllStaff(ArrayList<Staff> staffDatabase) {
        for (int i = 0; i < staffDatabase.size(); i++) {
            System.out.println("Staff number " + (i + 1));
            staffDatabase.get(i).displayInformation();
        }
    }

    /*
     * This method list all staff in database
     * User then have the choice to choose 1 department to display info of all its staff
     * */
    public static void displayDepAndStaff(ArrayList<Department> departmentDatabase, ArrayList<Staff> staffDatabase, Scanner sc) {
        int answer = 0;
        boolean correctInput;
        //Display all department
        for (int i = 0; i < departmentDatabase.size(); i++) {
            System.out.println((i + 1) + ". " + departmentDatabase.get(i));
        }
        //Ask user to choose a department to show its staff, or quit the method
        do {
            correctInput = true;
            System.out.println("Please choose index number of department you want to display its staff\n" +
                    "(Or type 0 to quit.)");
            answer = sc.nextInt();
            int counter = 0;
            //If the input is valid (in range), we display its staff
            if (answer >= 1 && answer <= departmentDatabase.size()) {
                for (Staff aStaff : staffDatabase) {
                    if (aStaff.getDepartment().getId() == departmentDatabase.get(answer - 1).getId()) {
                        counter++;
                        System.out.println("Staff number "+counter);
                        aStaff.displayInformation();
                    }
                }
            }
            //If user type 0 , quit the method
            else if (answer == 0) System.out.println("Thank you.");
                //Other inputs mean wrong input
            else {
                System.out.println("Index out of range");
                correctInput = false;
            }

        } while (!correctInput);
    }

    /*
     * This method list salary of all staff
     * */
    public static void displaySalaryTable(ArrayList<Staff> staffDatabase) {
        Staff aStaff;
        for (int i = 0; i < staffDatabase.size(); i++) {
            aStaff = staffDatabase.get(i);
            System.out.printf((i + 1) + ". " +  "Id: "+aStaff.getId() + "\t" +"Name: "+ aStaff.getName()+ "\t" + "Salary: "+"%8.0f%n",aStaff.getSalary());
        }

    }

    /*
     * This method list salary of all staff in ascending order
     * */
    public static void displaySalaryTableInAscendingOrder(ArrayList<Staff> staffDatabase) {
        //Call the method "sortedStaffDatabase", it return the sorted version of staff database base on salary
        ArrayList<Staff> sortedStaffDatabase = sortStaffBaseOnSalary(staffDatabase);
        Staff aStaff;
        //Print out salary info in ascending order
        for (int i = 0; i < sortedStaffDatabase.size(); i++) {
            aStaff = sortedStaffDatabase.get(i);
            System.out.printf((i + 1) + ". " +  "Id: "+aStaff.getId() + "\t" +"Name: "+ aStaff.getName()+ "\t" + "Salary: "+"%8.0f%n",aStaff.getSalary());
        }
    }

    /*
     * This method list salary of all staff in descending order
     * */
    public static void displaySalaryTableInDescendingOrder(ArrayList<Staff> staffDatabase) {
        //Call the method "sortedStaffDatabase", it return the sorted version of staff database base on salary
        ArrayList<Staff> sortedStaffDatabase = sortStaffBaseOnSalary(staffDatabase);
        Staff aStaff;
        //Print out salary info in descending order
        for (int i = sortedStaffDatabase.size() - 1; i >= 0; i--) {
            aStaff = sortedStaffDatabase.get(i);
            System.out.printf((sortedStaffDatabase.size()-i) + ". " +  "Id: "+aStaff.getId() + "\t" +"Name: "+ aStaff.getName()+ "\t" + "Salary: "+"%8.0f%n",aStaff.getSalary());
        }
    }

    /*
     * This method sort staff database in salary ascending order
     * */
    public static ArrayList<Staff> sortStaffBaseOnSalary(ArrayList<Staff> staffDatabase) {
        //copy the staff database
        ArrayList<Staff> sortedStaffDatabase = new ArrayList<>();
        for (Staff aStaff : staffDatabase) {
            sortedStaffDatabase.add(aStaff);
        }
        //Sort it base on salary
        Collections.sort(sortedStaffDatabase);
        return sortedStaffDatabase;
    }
}



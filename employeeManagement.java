import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class employees {

    // nested String type list to hold employee data
    List<List<String>> emp;

    // constants to hold index of correspending related fields
    final int empID = 0,
            empName = 1,
            empPhNumber = 2;

    Scanner sc = new Scanner(System.in);

    // constructor for new employee arraylist
    employees() {
        emp = new ArrayList<>();
    }

    // new employee function for adding all the required fields
    boolean addNewEmployee() {

        String ID,
                name,
                phoneNumber;

        System.out.println("Enter Employeee ID ( 0000-9999 only ): ");

        // do-while until input employee is Valid
        do {
            ID = sc.nextLine();
        } while (!isValidId(ID));

        // name can hold any Character and string of any length
        System.out.println("Enter Name : ");
        name = sc.nextLine();

        System.out
                .println("Enter phone number (only digits allowed, use underscore \"_\" to leave field empty): ");

        // do-while number until is not valid or user choose to skip
        do {
            phoneNumber = sc.nextLine();
        } while (!isValidPhoneNumber(phoneNumber));

        // confirmation message
        System.out.println("\nNew employee added\n");

        // appending new employee data as a list to nested master list
        emp.add(new ArrayList<>(List.of(ID, name, phoneNumber)));

        return true;
    }

    // function to search and update specific employee ID
    boolean changeEmpId(String empIdToBeUpdated, String newEmpId) {

        for (List<String> person : emp) {
            if (person.get(empID).equals(empIdToBeUpdated)) {
                person.set(empID, newEmpId);
                return true;
            }
        }
        System.out.println("Employee not found");
        return false;
    }

    // function to search and update name of specific employee
    boolean changeName(String idToBeUpdated, String newName) {

        for (List<String> person : emp) {
            if (person.get(empID).equals(idToBeUpdated)) {
                person.set(empName, newName);
                return true;
            }
        }
        System.out.println("Employee not found");
        return false;
    }

    // function to search and upate phone number of specific employee
    boolean changePhoneNumber(String idToBeUpdated, String newPhoneNumber) {

        for (List<String> person : emp) {
            if (person.get(empID).equals(idToBeUpdated)) {
                person.set(empPhNumber, newPhoneNumber);
                return true;
            }
        }
        System.out.println("Employee not found");
        return false;
    }

    // function to to present choices to update employee data
    boolean updateEmployee(String idToBeUpdated) {

        String data;
        boolean updateStatus = false;

        // return false if list is empty
        if (isEmpty(emp)) {
            System.out.println("No data to update");
            return updateStatus;
        }
        // return false if employee does not exist
        if (!employeeIdExists(idToBeUpdated)) {
            System.out.println("Employee doesn't exist");
            return updateStatus;
        }

        // choices
        System.out.println("Enter choice ");
        System.out.println("1  change Emp ID ");
        System.out.println("2  change name  ");
        System.out.println("3  change phone number");

        int choice = sc.nextInt();
        sc.nextLine();

        // switch case for choice
        switch (choice) {
            case 1:
                System.out.println("Enter new Emp ID: ");

                // do-while until new id is valid
                do {
                    data = sc.nextLine();
                } while (!isValidId(data));
                updateStatus = changeEmpId(idToBeUpdated, data);
                break;

            case 2:
                System.out.println("Enter new Employee name: ");
                data = sc.nextLine();
                updateStatus = changeName(idToBeUpdated, data);
                break;
            case 3:
                System.out.println("Enter new Employee Phone number, use underscore \"_\" to leave field empty : ");

                // do-while new phone number is valid
                do {
                    data = sc.nextLine();
                } while (!isValidPhoneNumber(data));
                updateStatus = changePhoneNumber(idToBeUpdated, data);
                break;

            default:
                System.out.println("Invalid Choice");
                break;
        }
        // return true if changes are validated
        if (updateStatus)
            System.out.println("\nUpdate Successfully!\n");
        return updateStatus;
    }

    // function to delete employee data
    boolean deleteEmployee(String empIdToBeDeleted) {
        if (isValidId(empIdToBeDeleted))
            for (List<String> person : emp) {
                if (person.get(empID).equals(empIdToBeDeleted)) {
                    emp.remove(person);
                    System.out.println("Employee details Deleted successfully");
                    return true;
                }
            }
        System.out.println("\nEmployee ID doesn\'t exist\n");
        return false;
    }

    // function to check valid ID
    boolean isValidId(String idToCheck) throws IllegalArgumentException {
        try {
            if (employeeIdExists(idToCheck))
                throw new IllegalArgumentException("ID already exists,try using a different ID");
            if (!containsDigits(idToCheck) || idToCheck.length() != 4)
                throw new IllegalArgumentException("Invalid ID format, please Try again");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;

    }

    // function to check valid phone number
    boolean isValidPhoneNumber(String numberToCheck) throws IllegalArgumentException {
        try {
            if (numberToCheck.equals("_"))
                return true;
            if (!containsDigits(numberToCheck) || numberToCheck.length() != 10) {
                throw new IllegalArgumentException("Invalid phone number,please try again");

            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

            return false;
        }
        return true;

    }

    // function to check if ID is unique
    boolean employeeIdExists(String idToSearch) {
        for (List<String> person : emp) {
            if (person.get(empID).equals(idToSearch)) {
                return true;
            }
        }
        return false;
    }

    // function to display data of single employee
    boolean displaySpecificEmp(String searchId) {
        for (List<String> person : emp) {
            if (person.get(empID).equals(searchId)) {
                System.out.println("employee ID: " + person.get(empID));
                System.out.println("employee Name: " + person.get(empName));
                System.out.println(
                        "employee Phone Number: +91 " + person.get(empPhNumber));
                return true;
            }
        }
        System.out.println("ID not found");
        return false;
    }

    // function to display data of all employee
    void displayAll() {
        if (!isEmpty(emp)) {
            for (List<String> obj : emp) {
                System.out.println("employee ID: " + obj.get(empID));
                System.out.println("employee Name: " + obj.get(empName));
                System.out.println(
                        "employee Phone Number: " + obj.get(empPhNumber) + "\n______________________________");
            }
        } else
            System.out.println("No employee data found");
    }

    // function to check if list is empty
    boolean isEmpty(List<List<String>> listToBeChecked) {
        return listToBeChecked.size() == 0;
    }

    // function to check if string contains digits
    boolean containsDigits(String checkString) {
        for (int i = 0; i < checkString.length(); i++) {
            if (!(Character.isDigit(checkString.charAt(i))))
                return false;
        }
        return true;
    }
}

// main class
public class employeeManagement {

    // main function
    public static void main(String[] args) {

        // oject of new employee list
        employees empList = new employees();
        Scanner sc = new Scanner(System.in);

        int choice = 0;

        while (choice != 7) {

            // choices for CRUD operations
            System.out.println("\nEnter your choice out of the following : ");
            System.out.println("1  Create new employee");
            System.out.println("2  Update existing employee");
            System.out.println("3  Delete employee");
            System.out.println("4  Display all existing employee");
            System.out.println("5  Display Single Employee ID");
            System.out.println("6  Show number of employees");
            System.out.println("7  EXIT");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // new employee
                case 1:
                    empList.addNewEmployee();
                    break;

                // Update data
                case 2:
                    System.out.println("Enter empID to be updated");

                    String idToBeUpdated = sc.nextLine();

                    empList.updateEmployee(idToBeUpdated);
                    break;

                // delete employee data
                case 3:
                    System.out.println("Enter Employee ID");

                    idToBeUpdated = sc.nextLine();

                    empList.deleteEmployee(idToBeUpdated);
                    break;

                // display all employee data
                case 4:
                    empList.displayAll();
                    break;

                // display single employee data
                case 5:
                    System.out.println("Enter Employee ID: ");

                    idToBeUpdated = sc.nextLine();
                    empList.displaySpecificEmp(idToBeUpdated);

                    break;

                // return the number of employees
                case 6:
                    System.out.println("\nNumber of employee: " + empList.emp.size());
                    break;

                // default case
                default:
                    System.out.println("Invalid choice, try again");
                    break;

                /*
                 * EXIT if Choice happens to be 7
                 */
            }
        }
        sc.close();
    }
}

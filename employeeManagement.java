import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class employees {
    List<List<String>> emp;
    final int empID = 0, empName = 1, empPhNumber = 2;
    Scanner sc = new Scanner(System.in);

    employees() {
        emp = new ArrayList<>();
    }

    boolean addNewEmployee() throws IOException {
        String ID, name, phoneNumber;

        try {
            System.out.println("Enter Employeee ID ( 0000-9999 only ): ");
            ID = sc.nextLine();
            System.out.println("Enter Name : ");
            name = sc.nextLine();
            System.out.println("Enter phone number (only digits allowed): ");
            phoneNumber = sc.nextLine();

            if (ID.length() != 4 ||
                    ID.length() == 0 ||
                    !(containsDigits(ID)) ||
                    phoneNumber.length() != 10 ||
                    !(containsDigits(phoneNumber))) {
                throw new IOException("Invalid Input arguements");
            }

        } catch (IOException e) {
            System.out.println(e);
            return false;
        }

        System.out.println("\nNew employee added\n");
        emp.add(new ArrayList<>(List.of(ID, name, phoneNumber)));

        return true;
    }

    boolean changeEmpId(String empIdToBeUpdated, String newEmpId) {

        if (employeeIdExists(newEmpId)) {
            System.out.println("Employee already exists with similar ID");
            return false;
        } else
            for (List<String> person : emp) {
                if (person.get(empID).equals(empIdToBeUpdated)) {
                    person.set(empID, newEmpId);
                    return true;
                }
            }
        return false;
    }

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

    boolean updateEmployee(String idToBeUpdated) {
        
        if (emp.size() == 0) {
            System.out.println("No data to update");
            return false;
        }
        String data = "";
        boolean updateStatus = false;

        if (!(employeeIdExists(idToBeUpdated))) {
            System.out.println("Employee doesn't exist");

            return updateStatus;
        }
        System.out.println("Enter choice ");
        System.out.println("1  change Emp ID ");
        System.out.println("2  change name  ");
        System.out.println("3 change phone number");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter new Emp ID: ");

                data = sc.nextLine();
                updateStatus = changeEmpId(idToBeUpdated, data);

                break;
            case 2:
                System.out.println("Enter new Employee name: ");
                data = sc.nextLine();
                updateStatus = changeName(idToBeUpdated, data);
                break;
            case 3:
                System.out.println("Eneter new Employee name: ");
                data = sc.nextLine();
                updateStatus = changePhoneNumber(idToBeUpdated, data);
                break;

            default:
                System.out.println("Invalid Choice");
                break;
        }

        if (updateStatus)
            System.out.println("\nUpdate Successfully!\n");
        return updateStatus;
    }

    boolean deleteEmployee(String empIdToBeDeleted) {
        if (employeeIdExists(empIdToBeDeleted))
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

    boolean employeeIdExists(String idToSearch) {
        for (List<String> person : emp) {
            if (person.get(empID).equals(idToSearch)) {
                return true;
            }
        }
        return false;
    }

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

    void displayAll() {
        if (!isEmpty()) {
            for (List<String> obj : emp) {
                System.out.println("employee ID: " + obj.get(empID));
                System.out.println("employee Name: " + obj.get(empName));
                System.out.println(
                        "employee Phone Number: +91 " + obj.get(empPhNumber) + "\n______________________________");
            }
        } else {
            System.out.println("No employee data found");
        }

    }

    boolean isEmpty() {
        return emp.size() == 0;
    }

    boolean containsDigits(String checkString) {
        for (int i = 0; i < checkString.length(); i++) {
            if (!(Character.isDigit(checkString.charAt(i))))
                return false;
        }
        return true;
    }

}

public class employeeManagement {

    public static void main(String[] args) throws IOException {
        employees empList = new employees();
        Scanner sc = new Scanner(System.in);

        int choice = 0;

        while (choice != 7) {

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
                case 1:
                    empList.addNewEmployee();
                    break;
                case 2:
                    System.out.println("Enter empID to be updated");

                    String idToBeUpdated = sc.nextLine();

                    empList.updateEmployee(idToBeUpdated);
                    break;

                case 3:
                    System.out.println("Enter Employee ID");

                    idToBeUpdated = sc.nextLine();

                    empList.deleteEmployee(idToBeUpdated);
                    break;
                case 4:
                    empList.displayAll();
                    break;
                case 5:
                    System.out.println("Enter Employee ID: ");

                    idToBeUpdated = sc.nextLine();
                    empList.displaySpecificEmp(idToBeUpdated);

                    break;
                case 6:
                    System.out.println("\nNumber of employee: " + empList.emp.size());
                    break;
                default:
                    break;
            }
        }
        sc.close();
    }
}

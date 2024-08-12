import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Payroll payroll = new Payroll();

    public static void main(String[] args) {
        try {
            while (true) {
                displayMenu();
                int choice = getValidInt("Enter your choice: ");

                switch (choice) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        removeEmployee();
                        break;
                    case 3:
                        displayAllEmployees();
                        break;
                    case 4:
                        displayTotalPayroll();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } finally {
            scanner.close(); // Ensure the scanner is closed when done
        }
    }

    private static void displayMenu() {
        System.out.println("1. Add Employee");
        System.out.println("2. Remove Employee");
        System.out.println("3. Display All Employees");
        System.out.println("4. Display Total Payroll");
        System.out.println("5. Exit");
    }

    private static void addEmployee() {
        try {
            int id = getValidInt("Enter employee ID: ");
            if (payroll.findEmployeeById(id) != null) {
                System.out.println("Employee ID already exists. Please use a unique ID.");
                return;
            }

            String name = getString("Enter employee name: ");
            double salary = getValidDouble("Enter employee salary: ");
            String department = getString("Enter employee department: ");

            Employee employee = new Employee(id, name, salary, department);
            payroll.addEmployee(employee);
            System.out.println("Employee added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    private static void removeEmployee() {
        try {
            int id = getValidInt("Enter employee ID to remove: ");
            Employee employee = payroll.findEmployeeById(id);
            if (employee == null) {
                System.out.println("Employee with ID " + id + " not found.");
                return;
            }
            payroll.removeEmployee(id);
            System.out.println("Employee removed successfully.");
        } catch (Exception e) {
            System.out.println("Error removing employee: " + e.getMessage());
        }
    }

    private static void displayAllEmployees() {
        if (payroll.getEmployees().isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            payroll.displayAllEmployees();
        }
    }

    private static void displayTotalPayroll() {
        double total = payroll.calculateTotalPayroll();
        System.out.printf("Total Payroll: %.2f\n", total);
    }

    private static int getValidInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private static double getValidDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}

import java.util.ArrayList;
import java.util.List;

public class Payroll {
    private List<Employee> employees;

    public Payroll() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(int id) {
        employees.removeIf(emp -> emp.getId() == id);
    }

    public double calculateTotalPayroll() {
        return employees.stream().mapToDouble(Employee::getSalary).sum();
    }

    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }
        employees.forEach(employee -> {
            employee.displayDetails();
            System.out.println("------------------------------");
        });
    }

    public Employee findEmployeeById(int id) {
        return employees.stream().filter(emp -> emp.getId() == id).findFirst().orElse(null);
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees); // Return a copy to prevent external modifications
    }
}

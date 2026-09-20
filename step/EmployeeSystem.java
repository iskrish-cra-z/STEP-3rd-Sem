/**
 * Problem F2: Extending Employee Without Touching It
 */
class Employee {
    private String empId;
    private String empName;
    private double salary;

    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    public ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    public InternEmployee(String empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    public double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

public class EmployeeSystem {
    public static void main(String[] args) {
        Employee plain = new Employee("E001", "Karan", 40000);
        ManagerEmployee manager = new ManagerEmployee("M001", "Divya", 70000, 8000);
        InternEmployee intern = new InternEmployee("I001", "Meera", 12000, 10000);

        System.out.println("Plain: salary 40000");
        System.out.println("Manager: salary 70000, teamBonus 8000");
        System.out.println("Intern: salary 12000, stipendCap 10000\n");

        Employee[] employees = {plain, manager, intern};

        for (Employee emp : employees) {
            if (emp instanceof ManagerEmployee) {
                System.out.println("Manager effective pay: Rs " + ((ManagerEmployee) emp).effectiveSalary());
            } else if (emp instanceof InternEmployee) {
                System.out.println("Intern effective pay: Rs " + ((InternEmployee) emp).effectiveSalary());
            } else {
                System.out.println("Plain employee pay: Rs " + emp.getSalary());
            }
        }
    }
}

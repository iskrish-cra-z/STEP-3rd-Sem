/**
 * Problem F5: Capstone: A Small HR + Parking Allocation Mini-System
 */

// Reusing Employee and ManagerEmployee logic
class Employee {
    String empId;
    String empName;
    double salary;

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
    double teamBonus;

    public ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

// Reuse ParkingSlot logic
class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;

    public ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    public boolean allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            return true;
        }
        return false;
    }
}

public class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;
    
    static int totalRecords = 0;

    public CompanyEmployeeRecord(String name, String empId, Employee employee) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        totalRecords++;
    }

    public String fullProfile() {
        String slotDisplay = (slot != null) ? slot.slotNo : "no parking assigned";
        
        double pay = 0.0;
        if (employee != null) {
            if (employee instanceof ManagerEmployee) {
                pay = ((ManagerEmployee) employee).effectiveSalary();
            } else {
                pay = employee.getSalary();
            }
        }
        
        return name + " | Pay: Rs " + pay + " | Slot: " + slotDisplay;
    }

    public static void main(String[] args) {
        ParkingSlot slot1 = new ParkingSlot("A1", 4, 3);
        ParkingSlot slot2 = new ParkingSlot("A2", 5, 4);
        
        CompanyEmployeeRecord r1 = new CompanyEmployeeRecord("Divya", "E001", new ManagerEmployee("E001", "Divya", 70000, 8000));
        CompanyEmployeeRecord r2 = new CompanyEmployeeRecord("Karan", "E002", new Employee("E002", "Karan", 40000));
        
        // Simulating InternEmployee logic manually for Meera without creating the class
        Employee internEquivalent = new Employee("E003", "Meera", 10000); 
        CompanyEmployeeRecord r3 = new CompanyEmployeeRecord("Meera", "E003", internEquivalent);
        
        // Allot parking to 2 employees
        if (slot1.allot("V1")) r1.slot = slot1;
        if (slot2.allot("V2")) r2.slot = slot2;
        
        System.out.println("3 records; parking allotted to 2 of them\n");
        
        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}

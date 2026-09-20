/**
 * Problem F4: Designing the Instance/Static Boundary
 */

// Broken version for demonstration:
/*
class SrmStudentBroken {
    // Wrong: marked static, meaning ALL students share the same name
    static String name; 
    // Wrong: marked static, meaning ALL students share the same regNo
    static String regNo; 
    // Wrong: marked static, meaning ALL students share the same attendance
    static int attendance; 
    
    // Correct: belongs to the whole class
    static String university = "SRM"; 
    // Correct: counter needs to be shared to increment globally
    static int admissionCount = 0; 
}
*/

public class SrmStudentFixed {
    // Correctly changed to instance variables
    String name;
    String regNo;
    int attendance;
    
    // Static variables shared across all instances
    static String university = "SRM";
    static int admissionCount = 0;

    public SrmStudentFixed(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        admissionCount++;
        this.regNo = "RA2311003010" + (10 + admissionCount); 
    }

    public void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    public static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + admissionCount);
    }

    public static void main(String[] args) {
        // Run corrected version
        SrmStudentFixed student1 = new SrmStudentFixed("Ravi", 80);
        SrmStudentFixed student2 = new SrmStudentFixed("Meera", 90);
        
        student1.printIdCard();
        student2.printIdCard();
        SrmStudentFixed.printTotalAdmissions();
    }
}

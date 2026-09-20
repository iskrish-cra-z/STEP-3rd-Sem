/**
 * Problem F5: Capstone: A Small Fee + Hostel Management Mini-System
 */
class FeeAccount {
    String regNo;
    double totalFee;
    double amountPaid;

    public FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0;
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
        } else {
            System.out.println("Payment rejected (negative amount)");
        }
    }

    public double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {
    public HostelFeeAccount(String regNo, double totalFee) {
        super(regNo, totalFee);
    }
}

class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    public HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    public boolean allot(String name) {
        if (occupied < beds) {
            occupied++;
            return true;
        }
        return false;
    }
}

public class CapstoneSrmStudent {
    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    HostelRoom room;
    
    static int totalStudents = 0;

    public CapstoneSrmStudent(String name, String regNo, HostelFeeAccount feeAccount) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        totalStudents++;
    }

    public String fullStatus() {
        String roomDisplay = (room != null) ? room.roomNo : "unallotted";
        double due = (feeAccount != null) ? feeAccount.getDue() : 0.0;
        return name + " | Due: Rs " + due + " | Room: " + roomDisplay;
    }

    public static void main(String[] args) {
        HostelRoom room1 = new HostelRoom("C-214", 3, 2);
        HostelRoom room2 = new HostelRoom("C-507", 2, 1);
        
        CapstoneSrmStudent s1 = new CapstoneSrmStudent("Ravi", "R001", new HostelFeeAccount("R001", 200000));
        CapstoneSrmStudent s2 = new CapstoneSrmStudent("Anitha", "R002", new HostelFeeAccount("R002", 200000));
        CapstoneSrmStudent s3 = new CapstoneSrmStudent("Karthik", "R003", new HostelFeeAccount("R003", 200000));
        
        // Allot rooms to 2 students
        if (room1.allot(s1.name)) s1.room = room1;
        if (room2.allot(s2.name)) s2.room = room2;
        
        System.out.println("3 students; rooms allotted to 2 of them;");
        System.out.println("one payment rejected (negative amount)\n");

        // Process payments
        s1.feeAccount.pay(60000); // valid
        s2.feeAccount.pay(20000); // valid
        s3.feeAccount.pay(-500);  // rejected
        
        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());
        System.out.println("\nTotal students: " + CapstoneSrmStudent.totalStudents);
    }
}

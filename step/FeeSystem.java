/**
 * Problem F2: Extending FeeAccount Without Touching It
 */
class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    public FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0;
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
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

    public void payInTwoInstallments(double amount) {
        if (amount > 0) {
            pay(amount / 2);
            pay(amount / 2);
        }
    }
}

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;

    public ScholarshipFeeAccount(String regNo, double totalFee, double scholarshipPercent) {
        super(regNo, totalFee);
        this.scholarshipPercent = scholarshipPercent;
    }

    public double effectiveDue() {
        double due = getDue();
        return due - (due * scholarshipPercent / 100);
    }
}

public class FeeSystem {
    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("R001", 150000);
        HostelFeeAccount hostel = new HostelFeeAccount("R002", 200000);
        ScholarshipFeeAccount scholarship = new ScholarshipFeeAccount("R003", 180000, 20);

        plain.pay(150000);
        hostel.payInTwoInstallments(60000);
        // scholarship paid 0

        System.out.println("Plain: totalFee 150000, paid 150000");
        System.out.println("Hostel: totalFee 200000, paid 60000");
        System.out.println("Scholarship: totalFee 180000, paid 0, scholarship 20%\n");

        FeeAccount[] accounts = {plain, hostel, scholarship};

        for (FeeAccount acc : accounts) {
            if (acc instanceof ScholarshipFeeAccount) {
                System.out.println("Scholarship account effective due: Rs " + ((ScholarshipFeeAccount) acc).effectiveDue());
            } else if (acc instanceof HostelFeeAccount) {
                System.out.println("Hostel account due: Rs " + acc.getDue());
            } else {
                System.out.println("Plain account due: Rs " + acc.getDue());
            }
        }
    }
}

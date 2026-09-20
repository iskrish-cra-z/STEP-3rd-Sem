/**
 * Problem 3: Quarterly Bonus Calculator
 * Week 7 - Abstract Classes & Interfaces
 */
interface Auditable {
    String auditRecord();
}

abstract class StaffMember {
    private double baseSalary;
    protected double bonusRate;

    // Single-arg constructor chains to two-arg via this(...)
    public StaffMember(double baseSalary) {
        this(baseSalary, 0.10);
    }

    public StaffMember(double baseSalary, double bonusRate) {
        this.baseSalary = baseSalary;
        this.bonusRate = bonusRate;
    }

    public double getSalary() {
        return baseSalary;
    }

    public void setSalary(double baseSalary) {
        if (baseSalary < 0) {
            System.out.println("rejected, salary unchanged");
        } else {
            this.baseSalary = baseSalary;
        }
    }

    public abstract double calculateBonus();
}

class TeamLead extends StaffMember implements Auditable {
    private int teamSize;

    // Constructor chaining to StaffMember default (10% bonus rate)
    public TeamLead(double baseSalary, int teamSize) {
        super(baseSalary);
        this.teamSize = teamSize;
    }

    public TeamLead(double baseSalary, double bonusRate, int teamSize) {
        super(baseSalary, bonusRate);
        this.teamSize = teamSize;
    }

    @Override
    public double calculateBonus() {
        return getSalary() * bonusRate;
    }

    @Override
    public String auditRecord() {
        return "TeamLead audit: " + teamSize + " team members, salary $" + getSalary();
    }
}

public class QuarterlyBonusCalculator {
    static String getAuditIfApplicable(StaffMember s) {
        // instanceof check against the Auditable interface — not a specific class
        if (s instanceof Auditable) {
            Auditable a = (Auditable) s;
            return a.auditRecord();
        }
        return "No audit required";
    }

    public static void main(String[] args) {
        TeamLead t = new TeamLead(60000, 5);
        System.out.println(t.calculateBonus()); // 6000.0

        TeamLead t2 = new TeamLead(60000, 0.20, 5);
        System.out.println(t2.calculateBonus()); // 12000.0

        t.setSalary(-5000); // rejected

        // Upcasting: TeamLead stored as its parent type StaffMember
        StaffMember ref = t;
        System.out.println(getAuditIfApplicable(ref));
    }
}

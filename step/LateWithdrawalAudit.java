import java.util.Arrays;

/**
 * Problem 3: The Late-Withdrawal Penalty Override & Audit Trail
 */
class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;
    private double[] lateFeeHistory = new double[10];
    private int lateFeeCount = 0;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().isEmpty() || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bibNumber.");
        }
        this.bibNumber = bibNumber.trim();
        this.entryFee = entryFee;
        this.amountPaid = 0.0;
    }

    public void pay(double amount) {
        if (amount > 0) this.amountPaid += amount;
    }

    public double getBalanceDue() {
        return Math.max(0, entryFee - amountPaid);
    }

    protected void applyLateFee(double amount) {
        if (amount > 0) {
            entryFee += amount; // Increases balance due
            if (lateFeeCount < lateFeeHistory.length) {
                lateFeeHistory[lateFeeCount++] = amount;
            }
        }
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }
}

class RunnerEntry extends RaceEntry {
    private String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    protected void applyLateFee(double amount) {
        // Double the penalty and delegate to parent — no separate recording
        super.applyLateFee(amount * 2);
    }
}

public class LateWithdrawalAudit {
    public static void main(String[] args) {
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        r.applyLateFee(20);
        System.out.println(r.getBalanceDue()); // Expected: 90.0

        double[] history = r.getLateFeeHistory();
        System.out.println(Arrays.toString(history)); // [40.0]
        history[0] = 999;
        System.out.println(Arrays.toString(r.getLateFeeHistory())); // [40.0] unchanged
    }
}

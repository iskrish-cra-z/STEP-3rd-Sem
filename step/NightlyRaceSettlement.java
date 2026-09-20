/**
 * Problem 5: Race-Wide Bib Issuance, Discount Codes & Nightly Settlement Engine
 */
class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

    public final String entryCode;
    private static int bibCounter = 0;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().isEmpty() || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bibNumber.");
        }
        this.bibNumber = bibNumber.trim();
        this.entryFee = entryFee;
        this.amountPaid = 0.0;
        bibCounter++;
        this.entryCode = "ENT-" + (1000 + bibCounter);
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
        }
    }

    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        this.pay(amount); // Reuse flat version — no logic duplication
    }

    public double getBalanceDue() {
        return Math.max(0, entryFee - amountPaid);
    }

    public static boolean isValidDiscountCode(String code) {
        // Must be exactly 5 chars: 'M' + 3 digits + 1 uppercase letter
        if (code == null || code.length() != 5) return false;
        if (code.charAt(0) != 'M') return false;
        for (int i = 1; i <= 3; i++) {
            if (!Character.isDigit(code.charAt(i))) return false;
        }
        if (!Character.isUpperCase(code.charAt(4))) return false;
        return true;
    }

    public static int getBibCounter() {
        return bibCounter;
    }
}

class RunnerEntry extends RaceEntry {
    private String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }
}

class EliteRunnerEntry extends RunnerEntry {
    private double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee, String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    public int getTeamSize() { return teamSize; }
}

public class NightlyRaceSettlement {
    public static String settleNight(RaceEntry[] entries) {
        if (entries == null) return "0 processed";

        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (RaceEntry entry : entries) {
            if (entry == null) {
                nullSkipped++;
            } else {
                processed++;
                if (entry instanceof RelayTeamEntry) {
                    relay++;
                } else {
                    individual++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + relay + " relay | " + individual + " individual";
    }

    public static void main(String[] args) {
        System.out.println(RaceEntry.isValidDiscountCode("M123A")); // true
        System.out.println(RaceEntry.isValidDiscountCode("M12A"));  // false
        System.out.println(RaceEntry.isValidDiscountCode("X123A")); // false

        EliteRunnerEntry eliteEntry = new EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry relayEntry = new RelayTeamEntry("BIB4001", 300, 4);

        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(10, "UPI");

        RaceEntry[] batch = {eliteEntry, null, relayEntry};
        System.out.println(settleNight(batch));

        System.out.println(RaceEntry.getBibCounter()); // 4 (3 above + r)
    }
}

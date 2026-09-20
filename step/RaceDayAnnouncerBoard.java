/**
 * Problem 4: The Race-Day Announcer Board
 */
class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

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

    public String announce() {
        return "Race Entry | Bib: " + bibNumber + " | Balance: " + getBalanceDue();
    }
}

class RunnerEntry extends RaceEntry {
    private String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + bibNumber + " | Category: " + category + " | Balance: " + getBalanceDue();
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    public int getTeamSize() { return teamSize; }

    @Override
    public String announce() {
        return "Relay Team | Bib: " + bibNumber + " | Team Size: " + teamSize + " | Balance: " + getBalanceDue();
    }
}

public class RaceDayAnnouncerBoard {
    public static String announceAll(RaceEntry[] entries) {
        if (entries == null) return "";

        StringBuilder sb = new StringBuilder();
        for (RaceEntry entry : entries) {
            if (entry != null) {
                // Polymorphic announce — no instanceof chain for printing
                sb.append(entry.announce());

                // instanceof-guarded downcast for relay-specific data only
                if (entry instanceof RelayTeamEntry) {
                    RelayTeamEntry relay = (RelayTeamEntry) entry;
                    sb.append(" [Team size via downcast: ").append(relay.getTeamSize()).append("]");
                }

                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RunnerEntry runnerEntry = new RunnerEntry("BIB2001", 80, "Open 10K");
        runnerEntry.pay(30);
        runnerEntry.pay(20);

        RelayTeamEntry relayEntry = new RelayTeamEntry("BIB4001", 300, 4);

        System.out.println(announceAll(new RaceEntry[]{runnerEntry, relayEntry}));
    }
}

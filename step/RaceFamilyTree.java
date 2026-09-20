/**
 * Problem 2: Three Shapes of One Race Family
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

    public String getCategory() { return category; }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + bibNumber + " | Category: " + category + " | Balance: " + getBalanceDue();
    }
}

class EliteRunnerEntry extends RunnerEntry {
    private double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee, String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    @Override
    public String announce() {
        return "Elite Runner | Bib: " + bibNumber + " | Category: " + getCategory() +
               " | Sponsor Bonus: " + sponsorBonus + " | Balance: " + getBalanceDue();
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

public class RaceFamilyTree {
    public static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        } else if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        } else if (entry instanceof RunnerEntry) {
            return "Single descendant (2 generations deep)";
        } else {
            return "Base class (1 generation)";
        }
    }

    public static double getTotalBalanceDue(RaceEntry[] entries) {
        double total = 0.0;
        if (entries != null) {
            for (RaceEntry entry : entries) {
                if (entry != null) {
                    total += entry.getBalanceDue();
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        RunnerEntry re = new RunnerEntry("BIB2001", 80, "Open 10K");
        EliteRunnerEntry ee = new EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry rt = new RelayTeamEntry("BIB4001", 300, 4);

        System.out.println(re.announce());
        System.out.println(ee.announce());
        System.out.println(rt.announce());

        System.out.println(classifyGeneration(ee));
        System.out.println(classifyGeneration(rt));

        System.out.println(getTotalBalanceDue(new RaceEntry[]{re, ee, rt}));
    }
}

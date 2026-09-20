/**
 * Problem 5: Nightly Fleet Reconciliation Engine
 */
class BusTicketAccount {
    String bookingId;
    double ticketFare;
    static double minPenaltyPercent;

    static {
        minPenaltyPercent = 1.0; 
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public final double calculatePenalty(int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0) return 0.0;
        if (minutesLate == 0) return 0.0;

        double penaltyPercent = 0.0;
        if (minutesLate >= 1) penaltyPercent += Math.min(5, minutesLate) * 0.5;
        if (minutesLate >= 6) penaltyPercent += Math.min(10, minutesLate - 5) * 1.0;
        if (minutesLate >= 16) penaltyPercent += (minutesLate - 15) * 2.0;
        
        penaltyPercent = Math.max(penaltyPercent, minPenaltyPercent);
        return (ticketFare * penaltyPercent) / 100.0;
    }
}

class SleeperAccount extends BusTicketAccount {
    public SleeperAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }
}

public class FleetReconciliationEngine {

    public static void processAccount(BusTicketAccount account, double amount, int minutesLate, double[] totalPenalties, int[] counters) {
        counters[0]++; // processed
        
        if (account instanceof SleeperAccount) {
            counters[2]++; // sleeper
        } else {
            counters[3]++; // regular
        }
        
        // Reconcile and calculate
        totalPenalties[0] += account.calculatePenalty(minutesLate);
    }

    public static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        // 0: processed, 1: nullSkipped, 2: sleeper, 3: regular
        int[] counters = new int[4]; 
        double[] totalPenalties = new double[1];

        // Ensure we don't crash due to mismatched lengths by stopping at the shortest provided dimension
        int minLen = Math.min(accounts.length, Math.min(amounts.length, minutesLateArray.length));

        for (int i = 0; i < minLen; i++) {
            if (accounts[i] == null) {
                counters[1]++;
                continue;
            }
            processAccount(accounts[i], amounts[i], minutesLateArray[i], totalPenalties, counters);
        }
        
        System.out.println(counters[0] + " processed | " + counters[1] + " null skipped | " + counters[2] + " sleeper | " + counters[3] + " regular | grand total penalties = " + totalPenalties[0]);
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
            new SleeperAccount("BK001", 2000), 
            null, 
            new BusTicketAccount("BK002", 1200)
        };
        double[] amounts = {1200, 900, 700};
        int[] minutesLateArray = {10, 5, 0};
        
        processBatch(accounts, amounts, minutesLateArray);
    }
}

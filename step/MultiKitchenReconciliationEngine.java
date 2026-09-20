/**
 * Problem 5: Nightly Multi-Kitchen Reconciliation Engine
 */
class DeliveryAccount {
    String studentId;
    double orderValue;
    static double minSurgePercent;

    static {
        minSurgePercent = 1.0; 
    }

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) return 0.0;
        if (delayMinutes == 0) return 0.0;

        double surgePercent = 0.0;
        if (delayMinutes >= 1) surgePercent += Math.min(5, delayMinutes) * 0.5;
        if (delayMinutes >= 6) surgePercent += Math.min(10, delayMinutes - 5) * 1.0;
        if (delayMinutes >= 16) surgePercent += (delayMinutes - 15) * 2.0;
        
        surgePercent = Math.max(surgePercent, minSurgePercent);
        return (orderValue * surgePercent) / 100.0;
    }
}

class PremiumAccount extends DeliveryAccount {
    public PremiumAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }
}

public class MultiKitchenReconciliationEngine {

    public static void processAccount(DeliveryAccount account, double amount, int delayMinutes, double[] totalSurgeFees, int[] counters) {
        counters[0]++; // processed
        
        if (account instanceof PremiumAccount) {
            counters[2]++; // premium
        } else {
            counters[3]++; // regular
        }
        
        totalSurgeFees[0] += account.calculateSurgeFee(delayMinutes);
    }

    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        int[] counters = new int[4]; // 0: processed, 1: nullSkipped, 2: premium, 3: regular
        double[] totalSurgeFees = new double[1];

        int minLen = Math.min(accounts.length, Math.min(amounts.length, delayMinutesArray.length));

        for (int i = 0; i < minLen; i++) {
            if (accounts[i] == null) {
                counters[1]++;
                continue;
            }
            
            // Apply reconciled amount to account before processing
            accounts[i].orderValue = amounts[i]; 
            processAccount(accounts[i], amounts[i], delayMinutesArray[i], totalSurgeFees, counters);
        }
        
        System.out.println(counters[0] + " processed | " + counters[1] + " null skipped | " + counters[2] + " premium | " + counters[3] + " regular | grand total surge fees = " + totalSurgeFees[0]);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new PremiumAccount("STU001", 500), 
            null, 
            new DeliveryAccount("STU002", 300)
        };
        double[] amounts = {500, 400, 300};
        int[] delayMinutesArray = {10, 5, 0};
        
        processBatch(accounts, amounts, delayMinutesArray);
    }
}

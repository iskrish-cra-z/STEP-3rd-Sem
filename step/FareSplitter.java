import java.util.Arrays;

/**
 * Problem 2: Remainder-Fair FareSplitter
 */
public class FareSplitter {
    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0) {
            throw new IllegalArgumentException("Total fare cannot be negative.");
        }
        if (passengerCount <= 0 && totalFare > 0) {
            throw new IllegalArgumentException("Passenger count must be positive when fare is > 0.");
        }
        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 1);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2); // Default to 2 for a provisional split per example output
    }

    public double[] fareBreakdown() {
        if (passengerCount <= 0 || totalFare == 0.0) {
            double[] arr = new double[Math.max(1, passengerCount)];
            Arrays.fill(arr, 0.0);
            return arr;
        }
        
        double[] shares = new double[passengerCount];
        int totalPaise = (int) Math.round(totalFare * 100);
        int baseSharePaise = totalPaise / passengerCount;
        int remainder = totalPaise % passengerCount;

        for (int i = 0; i < passengerCount; i++) {
            int currentSharePaise = baseSharePaise;
            // Distribute the remainder paisa evenly to the last passengers to ensure it sums correctly without truncation
            if (i >= passengerCount - remainder) {
                currentSharePaise++;
            }
            shares[i] = currentSharePaise / 100.0;
        }
        return shares;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FareSplitter("TRIP001", 100000, 3).fareBreakdown()));
        System.out.println(Arrays.toString(new FareSplitter("TRIP003").fareBreakdown()));
    }
}

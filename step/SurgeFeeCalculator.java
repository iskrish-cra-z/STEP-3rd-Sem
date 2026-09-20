/**
 * Problem 4: Exam-Week Surge Fee Calculator
 */
public class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Negative values are not allowed.");
        }
        if (delayMinutes == 0) {
            return 0.0;
        }

        double surgePercent = 0.0;
        
        // Bracket 1: minutes 1-5 (0.5% per minute)
        if (delayMinutes >= 1) {
            surgePercent += Math.min(5, delayMinutes) * 0.5;
        }
        // Bracket 2: minutes 6-15 (1.0% per minute)
        if (delayMinutes >= 6) {
            surgePercent += Math.min(10, delayMinutes - 5) * 1.0;
        }
        // Bracket 3: minutes 16+ (2.0% per minute)
        if (delayMinutes >= 16) {
            surgePercent += (delayMinutes - 15) * 2.0;
        }

        // Apply minimum floor only if genuinely delayed
        surgePercent = Math.max(surgePercent, minimumSurgePercent);

        return (orderValue * surgePercent) / 100.0;
    }

    public static void main(String[] args) {
        SurgeFeeCalculator calc = new SurgeFeeCalculator(1.0);
        System.out.println("Rs " + calc.calculateSurgeFee(500, 0));
        System.out.println("Rs " + calc.calculateSurgeFee(500, 1));
        System.out.println("Rs " + calc.calculateSurgeFee(500, 16));
    }
}

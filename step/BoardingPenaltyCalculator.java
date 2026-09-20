/**
 * Problem 4: Tiered Boarding Penalty Calculator
 */
public class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException("Negative values are not allowed.");
        }
        if (minutesLate == 0) {
            return 0.0;
        }

        double penaltyPercent = 0.0;
        
        // Bracket 1: minutes 1-5 (0.5% per minute)
        if (minutesLate >= 1) {
            penaltyPercent += Math.min(5, minutesLate) * 0.5;
        }
        // Bracket 2: minutes 6-15 (1.0% per minute)
        if (minutesLate >= 6) {
            penaltyPercent += Math.min(10, minutesLate - 5) * 1.0;
        }
        // Bracket 3: minutes 16+ (2.0% per minute)
        if (minutesLate >= 16) {
            penaltyPercent += (minutesLate - 15) * 2.0;
        }

        // Apply minimum floor only if they are genuinely late
        penaltyPercent = Math.max(penaltyPercent, minimumPenaltyPercent);

        return (ticketFare * penaltyPercent) / 100.0;
    }

    public static void main(String[] args) {
        BoardingPenaltyCalculator calc = new BoardingPenaltyCalculator(1.0);
        System.out.println("Rs " + calc.calculatePenalty(1000, 0));
        System.out.println("Rs " + calc.calculatePenalty(1000, 1));
        System.out.println("Rs " + calc.calculatePenalty(1000, 16));
    }
}

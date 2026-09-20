/**
 * Problem 3: Canteen Trust-Score Ranking Engine
 */
public class Canteen {
    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3); // Sensible default trust score
    }

    public int compareTo(Canteen other) {
        // Primary sort: trustScore DESCENDING (higher score first)
        int scoreDiff = Integer.compare(other.trustScore, this.trustScore);
        if (scoreDiff != 0) {
            return scoreDiff;
        }
        
        // Secondary sort (tie-breaker): canteenCode alphabetically case-insensitive
        int codeDiff = this.canteenCode.compareToIgnoreCase(other.canteenCode);
        if (codeDiff != 0) {
            return codeDiff;
        }
        
        // Tertiary sort (just in case): length of canteen name
        return Integer.compare(this.canteenName.length(), other.canteenName.length());
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {
        // O(n^2) stable sort
        for (int i = 0; i < canteens.length - 1; i++) {
            for (int j = 0; j < canteens.length - i - 1; j++) {
                if (canteens[j].compareTo(canteens[j + 1]) > 0) {
                    Canteen temp = canteens[j];
                    canteens[j] = canteens[j + 1];
                    canteens[j + 1] = temp;
                }
            }
        }
        return canteens;
    }

    public static void main(String[] args) {
        Canteen[] canteens = {
            new Canteen("HB3-C", "Spice Junction", 3),
            new Canteen("hb1-c", "Grand Mess", 5),
            new Canteen("HB2-C", "Southern Treats") // Defaults to 3
        };

        Canteen[] ranked = rankCanteens(canteens);
        
        System.out.print("[");
        for (int i = 0; i < ranked.length; i++) {
            System.out.print("\"" + ranked[i].canteenCode + "\"");
            if (i < ranked.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}

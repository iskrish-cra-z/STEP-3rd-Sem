/**
 * Problem 5: The Movie Review Word Length Profiler
 */
public class WordLengthProfiler {
    public static void classifyWordLengths(String review) {
        String[] words = review.split("\\s+");
        int shortCount = 0, mediumCount = 0, longCount = 0;

        for (String word : words) {
            // Remove punctuation from the word to count actual letters
            word = word.replaceAll("[^a-zA-Z]", "");
            int length = word.length();
            
            if (length >= 1 && length <= 4) {
                shortCount++;
            } else if (length >= 5 && length <= 8) {
                mediumCount++;
            } else if (length >= 9) {
                longCount++;
            }
        }

        System.out.printf("Short: %d | Medium: %d | Long: %d\n", shortCount, mediumCount, longCount);
    }

    public static void main(String[] args) {
        classifyWordLengths("This movie was absolutely fantastic and thrilling");
    }
}

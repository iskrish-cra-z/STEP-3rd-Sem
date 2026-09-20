import java.util.*;

/**
 * Problem 5: Stop-Word-Filtered Word Frequency Report
 */
public class WordFrequencyReport {
    public static void printFilteredWordFrequency(String feedback) {
        // Stop words list
        Set<String> stopWords = new HashSet<>(Arrays.asList("the", "was", "and", "a", "is", "of", "in"));
        
        // Normalize: lowercase and strip punctuation
        String cleanedText = feedback.toLowerCase().replaceAll("[.,;!?]", "");
        
        // Split into words
        String[] words = cleanedText.split("\\s+");
        
        // Count frequencies
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            if (word.isEmpty() || stopWords.contains(word)) {
                continue;
            }
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }
        
        // Sort by count descending
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordCounts.entrySet());
        sortedList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        
        // Print
        for (Map.Entry<String, Integer> entry : sortedList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    public static void main(String[] args) {
        printFilteredWordFrequency("The mentor was great, the session was great and clear.");
    }
}

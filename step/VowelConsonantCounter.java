/**
 * Problem 1: Vowel & Consonant Counter
 */
public class VowelConsonantCounter {
    public static void countVowelsAndConsonants(String text) {
        int vowels = 0;
        int consonants = 0;
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == ' ') continue;
            
            char lowerC = Character.toLowerCase(c);
            if (lowerC == 'a' || lowerC == 'e' || lowerC == 'i' || lowerC == 'o' || lowerC == 'u') {
                vowels++;
            } else if (Character.isLetter(c)) {
                consonants++;
            }
        }
        
        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }
    
    public static void main(String[] args) {
        countVowelsAndConsonants("Java Programming");
    }
}

/**
 * Problem 4: Library ISBN Normalizer & Validator
 */
public class LibraryIsbnValidator {
    public static String normalizeCode(String raw) {
        if (raw == null) return "";
        String trimmed = raw.trim();
        if (trimmed.length() < 3) return trimmed;
        
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }
    
    public static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: length must be exactly 13 characters";
        }
        
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }
        
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: remaining body must be digits";
            }
        }
        
        String pubCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7);
        
        StringBuilder formatted = new StringBuilder();
        formatted.append("[").append(pubCode).append("] ")
                 .append("YEAR: ").append(year)
                 .append(" | CATALOG: ").append(catalog);
                 
        return formatted.toString();
    }
    
    public static void main(String[] args) {
        String raw1 = " pen2026004251 ";
        String norm1 = normalizeCode(raw1);
        System.out.println(validateAndFormat(norm1));
        
        String raw2 = "12N2026004251";
        String norm2 = normalizeCode(raw2);
        System.out.println(validateAndFormat(norm2));
    }
}

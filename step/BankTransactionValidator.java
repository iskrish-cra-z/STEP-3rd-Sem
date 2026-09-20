/**
 * Problem 5: Bank Transaction Reference Generator & Validator
 */
public class BankTransactionValidator {
    public static String normalizeReference(String raw) {
        if (raw == null) return "";
        String trimmed = raw.trim();
        if (trimmed.length() < 3) return trimmed;
        
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }
    
    public static String validateAndFormat(String reference) {
        if (reference.length() != 14) {
            return "Invalid: length must be exactly 14 characters";
        }
        
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }
        
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: remaining body must be digits";
            }
        }
        
        String bankCode = reference.substring(0, 3);
        String date = reference.substring(3, 5) + "/" + reference.substring(5, 7) + "/" + reference.substring(7, 9);
        String seq = reference.substring(9);
        
        StringBuilder formatted = new StringBuilder();
        formatted.append("[").append(bankCode).append("] ")
                 .append("DATE: ").append(date)
                 .append(" | SEQ: ").append(seq);
                 
        return formatted.toString();
    }
    
    public static void main(String[] args) {
        String raw1 = " hdf03022600042 ";
        String norm1 = normalizeReference(raw1);
        System.out.println(validateAndFormat(norm1));
        
        String raw2 = "12F03022600042";
        String norm2 = normalizeReference(raw2);
        System.out.println(validateAndFormat(norm2));
    }
}

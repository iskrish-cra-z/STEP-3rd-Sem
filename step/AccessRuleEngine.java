/**
 * Problem 1: Field Visibility & Intake Validator
 */
class PatientRecord {
    private String patientId; // private: accessible only within this class
    String wardCode;          // default: accessible within the same package
    protected double vitalsScore; // protected: accessible within same package and subclasses
    public String facilityName;   // public: accessible everywhere

    public PatientRecord(String patientId, String wardCode, double vitalsScore, String facilityName) {
        if (patientId == null || patientId.trim().isEmpty()) {
            throw new IllegalArgumentException("patientId cannot be blank or whitespace-only.");
        }
        if (patientId.trim().length() < 4) {
            throw new IllegalArgumentException("patientId is too short to be a real ID.");
        }
        this.patientId = patientId.trim();
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }
}

public class AccessRuleEngine {
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier.equals("public")) return "ALLOWED";
        
        if (accessorContext.equals("SAME_CLASS")) return "ALLOWED";
        
        if (accessorContext.equals("SAME_PACKAGE")) {
            if (fieldModifier.equals("private")) return "DENIED";
            return "ALLOWED"; // default or protected
        }
        
        if (accessorContext.equals("DIFFERENT_PACKAGE")) {
            return "DENIED"; // only public is allowed cross-package
        }
        
        return "DENIED";
    }

    public static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;
        
        for (String[] attempt : attempts) {
            if (attempt.length < 2) continue;
            String result = classifyAccess(attempt[0], attempt[1]);
            if (result.equals("ALLOWED")) allowed++;
            else denied++;
        }
        
        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("default", "DIFFERENT_PACKAGE"));
        System.out.println(summarizeBatch(new String[][]{
            {"protected", "SAME_PACKAGE"},
            {"protected", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        }));
    }
}

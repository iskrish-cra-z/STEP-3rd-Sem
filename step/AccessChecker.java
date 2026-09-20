/**
 * Problem 1: Membership Field Reach Checker
 */
class LibraryMember {
    private String membershipId; // private
    String branchCode;           // default
    protected double finesOwed;  // protected
    public String displayName;   // public

    public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        if (membershipId == null || membershipId.trim().isEmpty()) {
            throw new IllegalArgumentException("membershipId cannot be blank or whitespace-only.");
        }
        if (membershipId.trim().length() < 4) {
            throw new IllegalArgumentException("membershipId is too short.");
        }
        this.membershipId = membershipId.trim();
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

public class AccessChecker {
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier.equals("public")) return "ALLOWED";
        
        if (accessorContext.equals("SAME_CLASS")) return "ALLOWED";
        
        if (accessorContext.equals("SAME_PACKAGE")) {
            if (fieldModifier.equals("private")) return "DENIED";
            return "ALLOWED";
        }
        
        if (accessorContext.equals("DIFFERENT_PACKAGE")) {
            return "DENIED"; 
        }
        
        return "DENIED";
    }

    public static String summarizeByModifier(String[][] attempts) {
        int[] allowed = new int[4]; // private, default, protected, public
        int[] denied = new int[4];
        
        for (String[] attempt : attempts) {
            if (attempt.length < 2) continue;
            String mod = attempt[0];
            String result = classifyAccess(mod, attempt[1]);
            
            int idx = -1;
            if (mod.equals("private")) idx = 0;
            else if (mod.equals("default")) idx = 1;
            else if (mod.equals("protected")) idx = 2;
            else if (mod.equals("public")) idx = 3;
            
            if (idx != -1) {
                if (result.equals("ALLOWED")) allowed[idx]++;
                else denied[idx]++;
            }
        }
        
        return "private: " + allowed[0] + " allowed / " + denied[0] + " denied | " +
               "default: " + allowed[1] + " allowed / " + denied[1] + " denied | " +
               "protected: " + allowed[2] + " allowed / " + denied[2] + " denied | " +
               "public: " + allowed[3] + " allowed / " + denied[3] + " denied";
    }

    public static void main(String[] args) {
        System.out.println(summarizeByModifier(new String[][]{
            {"private","SAME_CLASS"}, 
            {"private","SAME_PACKAGE"}, 
            {"default","SAME_PACKAGE"}, 
            {"default","DIFFERENT_PACKAGE"}, 
            {"protected","SAME_PACKAGE"}, 
            {"protected","SAME_CLASS"}, 
            {"public","DIFFERENT_PACKAGE"} 
        }));
    }
}

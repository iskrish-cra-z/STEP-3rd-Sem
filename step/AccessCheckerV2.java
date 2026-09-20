/**
 * Problem 2: Reference Desk Subclass Reach
 */
public class AccessCheckerV2 {
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
        
        if (accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
            if (fieldModifier.equals("protected")) return "ALLOWED";
            return "DENIED"; 
        }
        
        if (accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {
            return "DENIED"; 
        }
        
        return "DENIED";
    }

    public static String describeContext(String accessorContext) {
        if (accessorContext == null || accessorContext.isEmpty()) return "";
        String[] words = accessorContext.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
            }
            if (i < words.length - 1) sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
        System.out.println(describeContext("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
    }
}

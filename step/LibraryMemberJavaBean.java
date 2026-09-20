/**
 * Problem 4: LibraryMember JavaBean, Chained Constructors & Security Answer
 */
public class LibraryMemberJavaBean {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer; // Write-only

    // Constructor 1: Fully parameterized
    public LibraryMemberJavaBean(String membershipId, String name) {
        this.membershipId = membershipId;
        this.name = name;
        this.premiumMember = false;
    }

    // Constructor 2: Chaining
    public LibraryMemberJavaBean(String name) {
        this(null, name);
    }

    // Constructor 3: Chaining (No-arg constructor required for JavaBean)
    public LibraryMemberJavaBean() {
        this(null, null);
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        // Write-once property
        if (this.membershipId == null) {
            this.membershipId = membershipId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premiumMember) {
        this.premiumMember = premiumMember;
    }

    public void setSecurityAnswer(String securityAnswer) {
        if (securityAnswer != null) {
            this.securityAnswer = "SECURE_" + securityAnswer.hashCode();
        }
    }
    
    // No getSecurityAnswer() exists!

    public static void main(String[] args) {
        System.out.println(new LibraryMemberJavaBean("Priya Nair").getMembershipId());
        System.out.println(new LibraryMemberJavaBean("LIB-8841", "Priya Nair").getMembershipId());
        
        LibraryMemberJavaBean m = new LibraryMemberJavaBean(); 
        m.setMembershipId("LIB-8841"); 
        m.setMembershipId("FAKE-0000"); 
        System.out.println(m.getMembershipId());
    }
}

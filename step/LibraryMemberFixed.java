/**
 * Problem F4: Designing the Instance/Static Boundary for a Library Membership System
 */

// Broken version for demonstration:
/*
class LibraryMemberBroken {
    // Wrong: marked static, meaning ALL members share the same name
    static String name; 
    // Wrong: marked static, meaning ALL members share the same memberId
    static String memberId; 
    // Wrong: marked static, meaning ALL members share the same booksIssued count
    static int booksIssued; 
    
    // Correct: belongs to the whole library
    static String libraryName = "City Library"; 
    // Correct: counter needs to be shared to increment globally
    static int memberCount = 0; 
}
*/

public class LibraryMemberFixed {
    // Correctly changed to instance variables
    String name;
    String memberId;
    int booksIssued;
    
    // Static variables shared across all instances
    static String libraryName = "City Library";
    static int memberCount = 0;

    public LibraryMemberFixed(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount); 
    }

    public void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    public static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }

    public static void main(String[] args) {
        // Run corrected version
        LibraryMemberFixed member1 = new LibraryMemberFixed("Aditi", 2);
        LibraryMemberFixed member2 = new LibraryMemberFixed("Rohan", 5);
        
        member1.printMemberCard();
        member2.printMemberCard();
        LibraryMemberFixed.printTotalMembers();
    }
}

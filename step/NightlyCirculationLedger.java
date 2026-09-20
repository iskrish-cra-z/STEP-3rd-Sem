import java.util.Arrays;

/**
 * Problem 5: Immutable Loan Receipt & Nightly Circulation Ledger
 */
class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        
        if (bookIds == null) {
            throw new IllegalArgumentException("bookIds cannot be null");
        }
        
        for (String id : bookIds) {
            if (id == null || !id.matches("^BK-\\d{3}$")) {
                throw new IllegalArgumentException("Invalid book ID format: " + id);
            }
        }
        
        // Defensive copy coming in
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        // Defensive copy going out
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        String[] updatedIds = getBookIds(); 
        updatedIds[index] = newId;
        return new LoanReceipt(this.memberId, updatedIds); 
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
    
    public String getRoomNumber() {
        return roomNumber;
    }
}

public class NightlyCirculationLedger {
    
    // Shared state via static block
    static {
        System.out.println("NightlyCirculationLedger Initialized.");
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        if (receipts == null) return "0 processed";
        
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;
        
        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
            } else {
                processed++;
                if (receipt instanceof ReferenceOnlyLoanReceipt) {
                    referenceOnly++;
                } else {
                    regular++;
                }
            }
        }
        
        return processed + " processed | " + nullSkipped + " null skipped | " + referenceOnly + " reference-only | " + regular + " regular";
    }

    public static void main(String[] args) {
        try {
            new LoanReceipt("LIB-8841", new String[]{"BK-100", "bad"});
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
        
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100","BK-101"}); 
        String[] ids = r.getBookIds(); 
        ids[0] = "HACKED"; 
        System.out.println(r.getBookIds()[0]);
        
        LoanReceipt[] batch = {
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        System.out.println(processNightlyCirculation(batch));
    }
}

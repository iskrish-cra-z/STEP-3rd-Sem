/**
 * Problem 5: Fest-Wide Ticket Issuance, Promo Codes & Nightly Settlement Engine
 */
class EventTicket {
    protected double basePrice;
    protected double amountPaid;
    
    public final String ticketId;
    private static int ticketsIssued = 0;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
        this.amountPaid = 0.0;
        ticketsIssued++;
        this.ticketId = "TCK-" + (1000 + ticketsIssued);
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
        }
    }
    
    public void pay(double amount, String mode) {
        System.out.println("Paid via " + mode);
        this.pay(amount); // Reuse the flat version internally
    }

    public double getBalanceDue() {
        return Math.max(0, basePrice - amountPaid);
    }
    
    public static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5) return false;
        
        if (code.charAt(0) != 'F') return false;
        
        for (int i = 1; i <= 3; i++) {
            if (!Character.isDigit(code.charAt(i))) return false;
        }
        
        if (!Character.isUpperCase(code.charAt(4))) return false;
        
        return true;
    }
    
    public static int getTicketsIssued() {
        return ticketsIssued;
    }
}

class GroupTicket extends EventTicket {
    private int groupSize;
    
    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice);
        this.groupSize = groupSize;
    }
}

public class SettlementEngine {
    public static String processNightlySettlement(EventTicket[] tickets) {
        if (tickets == null) return "0 processed";
        
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;
        
        for (EventTicket ticket : tickets) {
            if (ticket == null) {
                nullSkipped++;
            } else {
                processed++;
                if (ticket instanceof GroupTicket) {
                    group++;
                } else {
                    individual++;
                }
            }
        }
        
        return processed + " processed | " + nullSkipped + " null skipped | " + group + " group | " + individual + " individual";
    }

    public static void main(String[] args) {
        EventTicket t1 = new EventTicket(500); 
        System.out.println(t1.ticketId);
        System.out.println(EventTicket.getTicketsIssued());
        
        System.out.println(EventTicket.isValidPromoCode("F123A"));
        System.out.println(EventTicket.isValidPromoCode("F12A"));
        System.out.println(EventTicket.isValidPromoCode("X123A"));
        
        t1.pay(200); 
        t1.pay(200, "UPI"); 
        System.out.println(t1.getBalanceDue());
        
        EventTicket[] batch = {
            new GroupTicket(2000, 5), 
            null, 
            new EventTicket(500)
        };
        System.out.println(processNightlySettlement(batch));
    }
}

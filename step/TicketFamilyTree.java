/**
 * Problem 2: Three Shapes of One Family Tree
 */
class EventTicket {
    protected String attendeeId;
    protected double basePrice;
    protected double amountPaid;

    public EventTicket(String attendeeId, double basePrice) {
        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
    }

    public void pay(double amount) {
        if (amount > 0) this.amountPaid += amount;
    }

    public double getBalanceDue() {
        return Math.max(0, basePrice - amountPaid);
    }
    
    public String printTicket() {
        return "Standard Event Ticket | Balance Due: " + getBalanceDue();
    }
}

class WorkshopTicket extends EventTicket {
    private String track;
    
    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }
    
    public String getTrack() { return track; }
    
    @Override
    public String printTicket() {
        return "Workshop Ticket | Track: " + track + " | Balance Due: " + getBalanceDue();
    }
}

class PremiumWorkshopTicket extends WorkshopTicket {
    private double kitFee;
    
    public PremiumWorkshopTicket(String attendeeId, double basePrice, String track, double kitFee) {
        super(attendeeId, basePrice, track);
        this.kitFee = kitFee;
        this.basePrice += kitFee; // reflect in total
    }
    
    @Override
    public String printTicket() {
        return "Premium Workshop Ticket | Track: " + getTrack() + " | Kit Fee: " + kitFee + " | Balance Due: " + getBalanceDue();
    }
}

class HackathonTicket extends EventTicket {
    private String teamName;
    
    public HackathonTicket(String attendeeId, double basePrice, String teamName) {
        super(attendeeId, basePrice);
        this.teamName = teamName;
    }
    
    @Override
    public String printTicket() {
        return "Hackathon Ticket | Team: " + teamName + " | Balance Due: " + getBalanceDue();
    }
}

public class TicketFamilyTree {
    public static String classifyGeneration(EventTicket ticket) {
        if (ticket instanceof PremiumWorkshopTicket) {
            return "Multilevel descendant (3 generations deep)";
        } else if (ticket instanceof HackathonTicket) {
            return "Hierarchical sibling (independent branch)";
        } else if (ticket instanceof WorkshopTicket) {
            return "Single descendant (2 generations deep)"; 
        } else {
            return "Base class (1 generation)";
        }
    }

    public static double getTotalBalanceDue(EventTicket[] tickets) {
        double total = 0.0;
        if (tickets != null) {
            for (EventTicket ticket : tickets) {
                if (ticket != null) {
                    total += ticket.getBalanceDue();
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(new EventTicket("STU1", 500).printTicket());
        System.out.println(new WorkshopTicket("STU2", 1200, "AI/ML").printTicket());
        PremiumWorkshopTicket pt = new PremiumWorkshopTicket("STU3", 2000, "Cloud Native", 300);
        System.out.println(pt.printTicket());
        HackathonTicket ht = new HackathonTicket("STU4", 800, "Byte Force");
        System.out.println(ht.printTicket());
        
        System.out.println(classifyGeneration(pt));
        System.out.println(classifyGeneration(ht));
    }
}

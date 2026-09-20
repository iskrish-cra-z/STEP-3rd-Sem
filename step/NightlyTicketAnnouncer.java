/**
 * Problem 4: The Nightly Ticket Announcer
 */
class EventTicket {
    protected double basePrice;
    
    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getBalanceDue() {
        return basePrice; 
    }
    
    public String printTicket() {
        return "Standard | Balance: " + getBalanceDue();
    }
}

class WorkshopTicket extends EventTicket {
    private String track;
    
    public WorkshopTicket(double basePrice, String track) {
        super(basePrice);
        this.track = track;
    }
    
    public String getTrack() {
        return track;
    }
    
    @Override
    public String printTicket() {
        return "Workshop | Track: " + track + " | Balance: " + getBalanceDue();
    }
}

public class NightlyTicketAnnouncer {
    public static String batchPrint(EventTicket[] tickets) {
        if (tickets == null) return "";
        
        StringBuilder sb = new StringBuilder();
        for (EventTicket ticket : tickets) {
            if (ticket != null) {
                // Polymorphic print
                sb.append(ticket.printTicket());
                
                // Downcast for workshop-specific detail
                if (ticket instanceof WorkshopTicket) {
                    WorkshopTicket wt = (WorkshopTicket) ticket;
                    sb.append(" [Track via downcast: ").append(wt.getTrack()).append("]");
                }
                
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        EventTicket[] tickets = {
            new EventTicket(500), 
            new WorkshopTicket(1200, "AI/ML")
        };
        System.out.println(batchPrint(tickets));
    }
}

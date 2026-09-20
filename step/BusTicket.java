import java.util.HashSet;
import java.util.Set;

/**
 * Problem 1: Bus Ticket Booking Validator
 */
public class BusTicket {
    private String passengerName;
    private String destination;
    private boolean checkedIn;

    // Parameterized constructor acting as a validation gate
    public BusTicket(String passengerName, String destination) {
        if (passengerName == null || passengerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid passenger name.");
        }
        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid destination.");
        }
        
        // Reject names that contain digits or special characters (allowing spaces)
        if (!passengerName.trim().matches("^[a-zA-Z\\s]+$")) {
            throw new IllegalArgumentException("Passenger name must contain only letters.");
        }

        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
        this.checkedIn = false;
    }

    public void markCheckedIn() {
        if (!checkedIn) {
            checkedIn = true;
        }
    }

    public static void processBatch(String[][] rawBookings) {
        int valid = 0;
        int rejected = 0;
        int duplicates = 0;
        Set<String> acceptedPairs = new HashSet<>();

        for (String[] booking : rawBookings) {
            if (booking.length < 2) {
                rejected++;
                continue;
            }
            
            String name = booking[0];
            String dest = booking[1];

            try {
                // Invalid input fails at construction time
                BusTicket ticket = new BusTicket(name, dest);
                
                // Duplicate detection
                String uniqueKey = ticket.passengerName.toLowerCase() + "|" + ticket.destination.toLowerCase();
                if (acceptedPairs.contains(uniqueKey)) {
                    duplicates++;
                } else {
                    acceptedPairs.add(uniqueKey);
                    valid++;
                }
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        System.out.println("Valid: " + valid + " | Rejected: " + rejected + " | Duplicates skipped: " + duplicates);
    }

    public static void main(String[] args) {
        String[][] batch = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };
        processBatch(batch);
    }
}

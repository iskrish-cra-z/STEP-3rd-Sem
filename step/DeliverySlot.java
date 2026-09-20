/**
 * Problem 2: ASAP or Scheduled — Delivery Slot Booking
 */
public class DeliverySlot {
    private String orderId;
    private String timeSlot;

    public DeliverySlot(String orderId, String timeSlot) {
        this.orderId = orderId;
        this.timeSlot = timeSlot;
    }

    public DeliverySlot(String orderId) {
        // timeSlot defaults to "ASAP" without duplicating field-setting logic
        this(orderId, "ASAP");
    }

    public boolean isPeakHour() {
        // Exactly these four slot values are peak hours
        return "12:00-13:00".equals(timeSlot) || 
               "13:00-14:00".equals(timeSlot) || 
               "19:00-20:00".equals(timeSlot) || 
               "20:00-21:00".equals(timeSlot);
    }

    public static void main(String[] args) {
        System.out.println(new DeliverySlot("ORD101", "13:00-14:00").isPeakHour());
        System.out.println(new DeliverySlot("ORD102").isPeakHour()); // Defaults to "ASAP"
    }
}

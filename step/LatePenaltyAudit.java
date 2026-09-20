import java.util.Arrays;

/**
 * Problem 3: The Late-Registration Penalty Override & Audit Trail
 */
class EventTicket {
    protected double basePrice;
    protected double amountPaid;
    private double[] lateFeeHistory = new double[10];
    private int lateFeeCount = 0;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
    }

    public void pay(double amount) {
        if (amount > 0) this.amountPaid += amount;
    }

    public double getBalanceDue() {
        return Math.max(0, basePrice - amountPaid);
    }
    
    protected void applyLateFee(double amount) {
        if (amount > 0) {
            basePrice += amount; // Increases the balance due
            if (lateFeeCount < lateFeeHistory.length) {
                lateFeeHistory[lateFeeCount++] = amount;
            }
        }
    }
    
    public double[] getLateFeeHistory() {
        // Return a defensive copy
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }
}

class WorkshopTicket extends EventTicket {
    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }
    
    @Override
    protected void applyLateFee(double amount) {
        // Reuse parent logic with doubled amount
        super.applyLateFee(amount * 2);
    }
}

public class LatePenaltyAudit {
    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket(1200);
        w.pay(1200);
        w.applyLateFee(100);
        System.out.println(w.getBalanceDue()); 
        
        double[] history = w.getLateFeeHistory();
        history[0] = 999;
        System.out.println(Arrays.toString(w.getLateFeeHistory()));
    }
}

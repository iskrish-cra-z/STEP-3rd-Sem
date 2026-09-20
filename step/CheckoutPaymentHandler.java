/**
 * Problem 1: Checkout Payment Handler
 * Week 7 - Abstract Classes & Interfaces
 */
abstract class PaymentMethod {
    public final String transactionId;
    private static int counter = 0;

    public PaymentMethod() {
        counter++;
        this.transactionId = "TXN-" + (1000 + counter);
    }

    public abstract String processPayment(double amount);

    // Overloaded — compile-time polymorphism
    public String processPayment(double amount, String note) {
        return processPayment(amount) + " (" + note + ")";
    }

    public String getTransactionId() {
        return transactionId;
    }
}

class CreditCardPayment extends PaymentMethod {
    private String cardNumberLastFour;

    public CreditCardPayment(String cardNumberLastFour) {
        super();
        this.cardNumberLastFour = cardNumberLastFour;
    }

    @Override
    public String processPayment(double amount) {
        return "Charged $" + amount + " to card ending " + cardNumberLastFour + " - Txn " + transactionId;
    }
}

class CashPayment extends PaymentMethod {
    public CashPayment() {
        super();
    }

    @Override
    public String processPayment(double amount) {
        return "Received $" + amount + " in cash - Txn " + transactionId;
    }
}

public class CheckoutPaymentHandler {
    static void printConfirmation(PaymentMethod payment, double amount) {
        // Polymorphic dispatch — no subclass check
        System.out.println(payment.processPayment(amount));
    }

    public static void main(String[] args) {
        CreditCardPayment cc = new CreditCardPayment("4471");
        System.out.println(cc.processPayment(250.0));

        CashPayment cash = new CashPayment();
        System.out.println(cash.processPayment(40.0));

        System.out.println(cc.processPayment(250.0, "Birthday gift"));

        // Upcasting: a CreditCardPayment stored as its parent type PaymentMethod
        PaymentMethod ref = cc;
        printConfirmation(ref, 250.0);
    }
}

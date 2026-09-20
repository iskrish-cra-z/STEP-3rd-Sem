/**
 * Problem 1: ATM PIN Length Validator
 */
public class AtmPinValidator {
    public static void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN \u2014 must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }
    
    public static void main(String[] args) {
        checkPinLength("482");
        checkPinLength("4820");
    }
}

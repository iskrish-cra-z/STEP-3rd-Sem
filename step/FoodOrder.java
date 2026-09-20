/**
 * Problem 1: Ghost Order Validator
 */
public class FoodOrder {
    private String studentName;
    private String dishName;
    private boolean delivered;

    // No no-argument constructor is provided.

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        if (dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Dish name cannot be empty");
        }
        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
        this.delivered = false;
    }

    public void markDelivered() {
        if (!delivered) {
            delivered = true;
        } else {
            System.out.println("Warning: Order already marked delivered! Double-serve detected.");
        }
    }

    public static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        for (String[] order : rawOrders) {
            if (order.length < 2) {
                rejected++;
                continue;
            }
            
            String name = order[0];
            String dish = order[1];

            try {
                // Constructor acts as a validation gate
                new FoodOrder(name, dish);
                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {
        String[][] batch = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };
        processBatch(batch);
    }
}

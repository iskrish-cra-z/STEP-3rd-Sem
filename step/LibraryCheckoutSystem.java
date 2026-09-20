/**
 * Problem 5: Community Library Checkout System
 * Week 7 - Abstract Classes & Interfaces
 */
interface Renewable {
    String renew();
}

interface Reservable {
    String reserve();
}

abstract class LibraryItem {
    public final String itemId;
    private static int counter = 0;

    public LibraryItem() {
        counter++;
        this.itemId = "LIB-" + (1000 + counter);
    }

    public String getItemId() {
        return itemId;
    }

    public abstract int getLoanPeriodDays();
}

// Textbook implements BOTH interfaces — IS-A LibraryItem, CAN-DO Renewable AND Reservable
class Textbook extends LibraryItem implements Renewable, Reservable {
    private String title;

    public Textbook(String title) {
        super();
        this.title = title;
    }

    @Override
    public int getLoanPeriodDays() {
        return 14;
    }

    @Override
    public String renew() {
        return title + " renewed";
    }

    @Override
    public String reserve() {
        return title + " reserved";
    }
}

// Magazine is a sibling of Textbook — implements only Renewable, not Reservable
class Magazine extends LibraryItem implements Renewable {
    private String title;

    public Magazine(String title) {
        super();
        this.title = title;
    }

    @Override
    public int getLoanPeriodDays() {
        return 7;
    }

    @Override
    public String renew() {
        return title + " renewed";
    }
}

// DigitalPass has NO relationship to LibraryItem — only Renewable
class DigitalPass implements Renewable {
    private String resourceName;

    public DigitalPass(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String renew() {
        return resourceName + " renewed";
    }
}

public class LibraryCheckoutSystem {
    static void processCheckouts(LibraryItem[] items) {
        for (LibraryItem item : items) {
            if (item != null) {
                System.out.println(item.getItemId() + " loan period: " + item.getLoanPeriodDays() + " days");
            }
        }
    }

    // Takes plain Object — Reservable and LibraryItem are completely independent
    static String reserveIfSupported(Object o) {
        if (o instanceof Reservable) {
            Reservable r = (Reservable) o;
            return r.reserve();
        }
        return "Reservation not supported";
    }

    public static void main(String[] args) {
        Textbook t = new Textbook("Java Fundamentals");
        System.out.println(t.getLoanPeriodDays());
        System.out.println(t.renew());
        System.out.println(t.reserve());

        Magazine m = new Magazine("Tech Monthly");
        System.out.println(reserveIfSupported(m));

        DigitalPass d = new DigitalPass("E-Journal Access");
        System.out.println(reserveIfSupported(d));

        // Upcasting: Textbook stored as its parent type LibraryItem
        LibraryItem ref = t;
        System.out.println(reserveIfSupported(ref));

        processCheckouts(new LibraryItem[]{t, m});
    }
}

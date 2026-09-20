/**
 * Problem F3: Object References, Null Safety, and a Mutating Method
 */
class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;

    public ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    public boolean allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            return true;
        }
        return false;
    }
}

public class ParkingAllocation {
    public static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        if (slots == null) return null;
        for (ParkingSlot slot : slots) {
            if (slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }
        return null;
    }

    // Passing the ParkingSlot array passes a reference to the array object, 
    // and its elements are references to the actual slot objects.
    // It does not copy the slots themselves, so calling allot() modifies the original objects.
    public static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot slot = findAvailableSlot(slots);
        if (slot != null) {
            if (slot.allot(vehicleNo)) {
                System.out.println(vehicleNo + " allotted to slot " + slot.slotNo);
            }
        } else {
            System.out.println("No slots available for " + vehicleNo);
        }
    }

    public static void main(String[] args) {
        ParkingSlot[] slots = {
            new ParkingSlot("A1", 4, 3),
            new ParkingSlot("A2", 5, 5)
        };
        
        System.out.println("Slots: A1 (3/4), A2 (5/5)");
        System.out.println("safeAllot(slots, \"TN09AB1234\")");
        safeAllot(slots, "TN09AB1234");
        System.out.println();
        
        System.out.println("Slots: A1 (4/4), A2 (5/5)");
        System.out.println("safeAllot(slots, \"TN09AB1234\")");
        safeAllot(slots, "TN09AB1234");
    }
}

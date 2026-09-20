/**
 * Problem F3: Object References, Null Safety, and a Mutating Method
 */
class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    public HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    public boolean allot(String name) {
        if (occupied < beds) {
            occupied++;
            return true;
        }
        return false;
    }
}

public class HostelAllocation {
    public static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        if (rooms == null) return null;
        for (HostelRoom room : rooms) {
            if (room.occupied < room.beds) {
                return room;
            }
        }
        return null;
    }

    // Passing the HostelRoom array passes a reference to the array object, 
    // and its elements are references to the actual room objects.
    // It does not copy the rooms themselves, so calling allot() modifies the original objects.
    public static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom room = findAvailableRoom(rooms);
        if (room != null) {
            if (room.allot(studentName)) {
                System.out.println(studentName + " allotted to room " + room.roomNo);
            }
        } else {
            System.out.println("No rooms available for " + studentName);
        }
    }

    public static void main(String[] args) {
        HostelRoom[] rooms = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };
        
        System.out.println("Rooms: C-214 (2/3), C-507 (2/2)");
        System.out.println("safeAllot(rooms, \"Divya\")");
        safeAllot(rooms, "Divya");
        System.out.println();
        
        System.out.println("Rooms: C-214 (3/3), C-507 (2/2)");
        System.out.println("safeAllot(rooms, \"Divya\")");
        safeAllot(rooms, "Divya");
    }
}

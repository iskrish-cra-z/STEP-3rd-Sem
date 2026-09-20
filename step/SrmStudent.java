/**
 * Problem F1: From Procedural Mess to a Working Attendance System
 */
public class SrmStudent {
    String name;
    String regNo;
    int attendance;

    public SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    public void addAttendanceUpdate(int newAttendance) {
        this.attendance = newAttendance;
    }

    public boolean isEligible() {
        return this.attendance >= 75;
    }

    // classAverage is static because it computes an aggregate over multiple student objects.
    // It doesn't belong to a single student's state, but rather operates on a collection of them.
    // isEligible is an instance method because it relies on a specific student's personal attendance.
    public static double classAverage(SrmStudent[] students) {
        if (students == null || students.length == 0) return 0.0;
        int sum = 0;
        for (SrmStudent s : students) {
            sum += s.attendance;
        }
        return (double) sum / students.length;
    }

    public static void main(String[] args) {
        SrmStudent[] students = {
            new SrmStudent("Ravi", "R001", 82),
            new SrmStudent("Anitha", "R002", 68),
            new SrmStudent("Karthik", "R003", 91),
            new SrmStudent("Meera", "R004", 74),
            new SrmStudent("Suresh", "R005", 60)
        };
        
        System.out.println("5 students:");
        System.out.println("Ravi 82, Anitha 68, Karthik 91, Meera 74, Suresh 60\n");

        for (SrmStudent s : students) {
            System.out.println(s.name + " - " + s.attendance + "% - " + (s.isEligible() ? "Eligible" : "Detained"));
        }
        
        System.out.println("Class average: " + SrmStudent.classAverage(students) + "%");
    }
}

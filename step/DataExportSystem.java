/**
 * Problem 2: One-Click Data Export
 * Week 7 - Category A Assignment
 */
interface Exportable {
    String exportData();
}

class ReportGenerator implements Exportable {
    private String reportName;
    private static int totalExports = 0;

    public ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        totalExports++;
        return "Exported report: " + reportName;
    }

    public static int getTotalExports() {
        return totalExports;
    }
}

class UserProfile implements Exportable {
    private String username;

    public UserProfile(String username) {
        this.username = username;
    }

    @Override
    public String exportData() {
        ReportGenerator.getTotalExports(); // Access shared counter via ReportGenerator
        // Increment the shared counter
        DataExportSystem.incrementExportCount();
        return "Exported profile: " + username;
    }
}

public class DataExportSystem {
    private static int totalExports = 0;

    static void incrementExportCount() {
        totalExports++;
    }

    static int getTotalExports() {
        return totalExports;
    }

    static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            if (item != null) {
                System.out.println(item.exportData());
            }
        }
    }

    public static void main(String[] args) {
        ReportGenerator r = new ReportGenerator("Sales Q1");
        System.out.println(r.exportData());

        UserProfile u = new UserProfile("jane_doe");
        System.out.println(u.exportData());

        // Upcasting: ReportGenerator stored as Exportable
        Exportable ref = new ReportGenerator("Q2 Report");
        exportAll(new Exportable[]{ref, u});
        System.out.println(getTotalExports());
    }
}

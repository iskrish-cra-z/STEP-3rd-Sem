import java.util.Arrays;

/**
 * Problem 5: Immutable Discharge Summary & Nightly Ledger
 */
class DischargeSummary {
    private final String patientId;
    private final String[] medicationCodes;

    public DischargeSummary(String patientId, String[] medicationCodes) {
        this.patientId = patientId;
        
        if (medicationCodes == null) {
            throw new IllegalArgumentException("medicationCodes cannot be null");
        }
        
        for (String code : medicationCodes) {
            if (code == null || !code.matches("^MED-[A-Z]$")) {
                throw new IllegalArgumentException("Invalid medication code format: " + code);
            }
        }
        
        // Defensive copy coming in
        this.medicationCodes = Arrays.copyOf(medicationCodes, medicationCodes.length);
    }

    public String getPatientId() {
        return patientId;
    }

    public String[] getMedicationCodes() {
        // Defensive copy going out
        return Arrays.copyOf(medicationCodes, medicationCodes.length);
    }

    // "wither" pattern to change a summary by returning a new object
    public DischargeSummary withCorrectedMedication(int index, String newCode) {
        if (index < 0 || index >= medicationCodes.length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        String[] updatedCodes = getMedicationCodes(); // Gets a defensive copy
        updatedCodes[index] = newCode;
        return new DischargeSummary(this.patientId, updatedCodes); // Constructor handles validation
    }
}

class CriticalCareDischargeSummary extends DischargeSummary {
    private final int icuDays;

    public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }
    
    public int getIcuDays() {
        return icuDays;
    }
}

public class NightlyLedger {
    
    // Shared one-time state setup via static block
    static {
        System.out.println("NightlyLedger Initialized.");
    }

    public static String processNightlyBatch(DischargeSummary[] summaries) {
        if (summaries == null) return "0 processed";
        
        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;
        
        for (DischargeSummary summary : summaries) {
            if (summary == null) {
                nullSkipped++;
            } else {
                processed++;
                if (summary instanceof CriticalCareDischargeSummary) {
                    criticalCare++;
                } else {
                    routine++;
                }
            }
        }
        
        return processed + " processed | " + nullSkipped + " null skipped | " + criticalCare + " critical-care | " + routine + " routine";
    }

    public static void main(String[] args) {
        DischargeSummary d = new DischargeSummary("MT2026-0142", new String[]{"MED-A","MED-B"}); 
        String[] codes = d.getMedicationCodes(); 
        codes[0] = "TAMPERED"; 
        System.out.println(d.getMedicationCodes()[0]);
        
        DischargeSummary[] batch = {
            new CriticalCareDischargeSummary("MT001", new String[]{"MED-X"}, 4),
            null,
            new DischargeSummary("MT002", new String[]{"MED-Y"})
        };
        System.out.println(processNightlyBatch(batch));
    }
}

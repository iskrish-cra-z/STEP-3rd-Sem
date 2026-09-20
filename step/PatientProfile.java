/**
 * Problem 4: PatientProfile JavaBean, Chained Constructors & Locker PIN
 */
public class PatientProfile {
    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPin; // Write-only property

    // Constructor 1: Fully parameterized
    public PatientProfile(String patientId, String name) {
        this.patientId = patientId;
        this.name = name;
        this.discharged = false;
    }

    // Constructor 2: Chaining
    public PatientProfile(String name) {
        this(null, name);
    }

    // Constructor 3: Chaining (No-arg constructor required for JavaBean)
    public PatientProfile() {
        this(null, null);
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        // Write-once property
        if (this.patientId == null) {
            this.patientId = patientId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    public void setLockerPin(String lockerPin) {
        if (lockerPin != null) {
            // One-way deterministic transformation
            this.lockerPin = "SECURE_" + lockerPin.hashCode();
        }
    }
    
    // No getLockerPin() exists!

    public static void main(String[] args) {
        System.out.println(new PatientProfile("Arjun Iyer").getPatientId());
        System.out.println(new PatientProfile("MT2026-0142", "Arjun Iyer").getPatientId());
        
        PatientProfile p = new PatientProfile(); 
        p.setPatientId("MT2026-0142"); 
        p.setPatientId("HACKED-0000"); 
        System.out.println(p.getPatientId());
    }
}

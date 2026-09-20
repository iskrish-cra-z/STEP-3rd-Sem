/**
 * Problem 2: Home Safety Alert Network
 * Week 7 - Abstract Classes & Interfaces
 */
interface Alertable {
    String sendAlert(String message);
}

class SecuritySensor {
    private String zoneName;

    public SecuritySensor(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneName() {
        return zoneName;
    }
}

class MotionSensor extends SecuritySensor implements Alertable {
    public MotionSensor(String zoneName) {
        super(zoneName);
    }

    @Override
    public String sendAlert(String message) {
        return "[" + getZoneName() + "] " + message;
    }
}

class DualZoneMotionSensor extends MotionSensor {
    private String secondZoneName;

    public DualZoneMotionSensor(String zoneName, String secondZoneName) {
        super(zoneName);
        this.secondZoneName = secondZoneName;
    }

    @Override
    public String sendAlert(String message) {
        // Reuse parent's message via super — no rebuilding from scratch
        return super.sendAlert(message) + " [also covering " + secondZoneName + "]";
    }
}

// No relation to SecuritySensor at all — pure capability via interface
class SmokeDetector implements Alertable {
    private String deviceId;

    public SmokeDetector(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String sendAlert(String message) {
        return "[" + deviceId + "] " + message;
    }
}

public class HomeSafetyAlertNetwork {
    static void broadcastAll(Alertable[] devices, String message) {
        for (Alertable device : devices) {
            if (device != null) {
                System.out.println(device.sendAlert(message));
            }
        }
    }

    static String getZoneIfMotionSensor(Alertable a) {
        if (a instanceof MotionSensor) {
            MotionSensor ms = (MotionSensor) a;
            return ms.getZoneName();
        }
        return "Not a motion sensor";
    }

    public static void main(String[] args) {
        MotionSensor m = new MotionSensor("Living Room");
        System.out.println(m.sendAlert("Motion detected"));

        DualZoneMotionSensor d = new DualZoneMotionSensor("Hallway", "Stairwell");
        System.out.println(d.sendAlert("Motion detected"));

        SmokeDetector s = new SmokeDetector("SD-01");
        System.out.println(s.sendAlert("Smoke detected"));

        System.out.println(getZoneIfMotionSensor(m));
        System.out.println(getZoneIfMotionSensor(s));

        broadcastAll(new Alertable[]{m, d, s}, "ALERT!");
    }
}

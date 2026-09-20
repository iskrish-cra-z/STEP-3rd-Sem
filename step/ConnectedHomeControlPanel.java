/**
 * Problem 5: Connected Home Control Panel
 */
abstract class HomeDevice {
    private static int counter = 0;
    private final String serialNumber;

    public HomeDevice() {
        counter++;
        this.serialNumber = "SN-" + (1000 + counter);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public abstract String activate();
}

interface RemoteControllable {
    String connect(String appId);
}

interface EnergyTrackable {
    double getConsumptionWatts();
}

class WashingMachine extends HomeDevice implements RemoteControllable, EnergyTrackable {
    private final double consumptionWatts;

    public WashingMachine(double consumptionWatts) {
        super();
        this.consumptionWatts = consumptionWatts;
    }

    @Override
    public String activate() {
        return "Washing machine " + getSerialNumber() + " started a cycle";
    }

    @Override
    public String connect(String appId) {
        return getSerialNumber() + " connected to " + appId;
    }

    @Override
    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}

class Refrigerator extends HomeDevice implements EnergyTrackable {
    private final double consumptionWatts;

    public Refrigerator(double consumptionWatts) {
        super();
        this.consumptionWatts = consumptionWatts;
    }

    @Override
    public String activate() {
        return "Refrigerator " + getSerialNumber() + " is now cooling";
    }

    @Override
    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}

class MobileApp implements RemoteControllable {
    private final String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public String connect(String appId) {
        // appId is the identifier of the home system or device hub
        return appName + " connected to " + appId;
    }
}

class ConnectedHomeControlPanel {
    public static void connectAll(RemoteControllable[] items, String appId) {
        for (RemoteControllable rc : items) {
            System.out.println(rc.connect(appId));
        }
    }

    public static Double getConsumptionIfTrackable(HomeDevice d) {
        if (d instanceof EnergyTrackable) {
            return ((EnergyTrackable) d).getConsumptionWatts();
        }
        return null; // indicates not trackable
    }

    public static void main(String[] args) {
        WashingMachine wm = new WashingMachine(500.0);
        System.out.println(wm.activate());
        System.out.println(wm.connect("HomeConnect"));

        Refrigerator fridge = new Refrigerator(150.0);
        System.out.println(fridge.activate());
        System.out.println("Fridge consumption: " + getConsumptionIfTrackable(fridge) + " W");

        MobileApp app = new MobileApp("HomeConnect App");
        System.out.println(app.connect("HomeConnect"));

        // Demonstrate connectAll with a mix of RemoteControllable objects
        RemoteControllable[] devices = new RemoteControllable[]{wm, app};
        System.out.println("--- Connecting all remote controllable devices ---");
        connectAll(devices, "HomeConnect");
    }
}

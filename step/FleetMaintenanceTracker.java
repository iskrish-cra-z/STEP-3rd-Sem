/**
 * Problem 3: Fleet Maintenance Tracker
 * Week 7 - Category A Assignment
 */
interface Insurable {
    String getInsuranceInfo();
}

abstract class ServiceableVehicle {
    private double mileage;

    public ServiceableVehicle() {
        this.mileage = 0.0;
    }

    public double getMileage() {
        return mileage;
    }

    public void addMileage(double km) {
        if (km < 0) {
            System.out.println("rejected, negative distance");
        } else {
            mileage += km;
        }
    }

    public abstract String performMaintenance();
}

class Forklift extends ServiceableVehicle implements Insurable {
    protected String assetTag;

    public Forklift(String assetTag) {
        super();
        this.assetTag = assetTag;
    }

    @Override
    public String performMaintenance() {
        return "Forklift " + assetTag + ": hydraulic and fork inspection complete";
    }

    @Override
    public String getInsuranceInfo() {
        return "Insured under fleet policy - Asset " + assetTag;
    }
}

class HeavyDutyForklift extends Forklift {
    public HeavyDutyForklift(String assetTag) {
        super(assetTag);
    }

    @Override
    public String performMaintenance() {
        // Reuse parent's maintenance steps via super — no restating from scratch
        return super.performMaintenance() + " | high pressure hydraulic check complete";
    }
}

public class FleetMaintenanceTracker {
    static String getInsuranceIfApplicable(ServiceableVehicle v) {
        if (v instanceof Insurable) {
            Insurable i = (Insurable) v;
            return i.getInsuranceInfo();
        }
        return "No insurance record exists";
    }

    public static void main(String[] args) {
        Forklift f = new Forklift("FL-22");
        f.addMileage(120);
        System.out.println(f.getMileage()); // 120.0

        System.out.println(f.performMaintenance());

        HeavyDutyForklift hd = new HeavyDutyForklift("HD-9");
        System.out.println(hd.performMaintenance());

        System.out.println(getInsuranceIfApplicable(f));
    }
}

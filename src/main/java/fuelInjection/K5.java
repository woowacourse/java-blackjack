package fuelInjection;

public class K5 extends Car {

    private static final int FUEL_EFFICIENCY = 13;
    private static final String NAME = "K5";

    private final double distance;

    public K5(double distance) {
        this.distance = distance;
    }

    @Override
    public double getDistancePerLiter() {
        return FUEL_EFFICIENCY;
    }

    @Override
    public double getTripDistance() {
        return distance;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

package fuelInjection;

public class Sonata extends Car {

    private static final int FUEL_EFFICIENCY = 10;
    private static final String NAME = "Sonata";

    private final double distance;

    public Sonata(double distance) {
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

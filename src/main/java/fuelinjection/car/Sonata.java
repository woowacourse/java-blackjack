package fuelinjection.car;

public class Sonata extends Car {

    private static final double DISTANCE_PER_LITER = 10;
    private static final String NAME = "Sonata";

    private final double tripDistance;

    public Sonata(double tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    public double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    public double getTripDistance() {
        return this.tripDistance;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

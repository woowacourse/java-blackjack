package oilinjection.car;

public class Sonata extends Car {

    private static final int DISTANCE_PRE_LITER = 10;
    private static final String NAME = "Sonata";

    private final double distance;

    public Sonata(double distance) {
        this.distance = distance;
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PRE_LITER;
    }

    @Override
    double getTripDistance() {
        return distance;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

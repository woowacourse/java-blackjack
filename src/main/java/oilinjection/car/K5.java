package oilinjection.car;

public class K5 extends Car {

    private static final int DISTANCE_PRE_LITER = 13;
    private static final String NAME = "K5";

    private final double distance;

    public K5(double distance) {
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

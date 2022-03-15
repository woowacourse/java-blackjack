package fuelinjection;

public class K5 extends Car {

    private static final double DISTANCE_PER_LITTER = 13;
    private static final String NAME = "K5";

    public K5(double tripDistance) {
        super(tripDistance);
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITTER;
    }

    @Override
    String getName() {
        return NAME;
    }
}

package study.car;

public class K5 extends Car {
    private static final String K5_NAME = "K5";
    private static final double K5_DISTANCE_PER_LITER = 13.0;

    private final double tripDistance;

    public K5(double tripDistance) {
        this.tripDistance = tripDistance;
        super.name = K5_NAME;
    }

    @Override
    double getDistancePerLiter() {
        return K5_DISTANCE_PER_LITER;
    }

    @Override
    double getTripDistance() {
        return this.tripDistance;
    }
}

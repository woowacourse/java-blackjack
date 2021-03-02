package rent;

public class K5 extends Car {

    private static final double DISTANCE_PER_LITER = 13;
    private static final String NAME = "K5";

    public K5(int distance) {
        super(distance);
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    double getTripDistance() {
        return tripDistance;
    }

    @Override
    String getName() {
        return NAME;
    }
}

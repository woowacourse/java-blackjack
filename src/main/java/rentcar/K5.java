package rentcar;

public class K5 extends Car {

    private static final String NAME = "K5";
    private static final double DISTANCE_PER_LITER = 13;

    private final int tripDistance;

    public K5(final int tripDistance) {
        this.tripDistance = tripDistance;
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

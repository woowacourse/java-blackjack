package rentCompany.car;

public class K5 extends Car {
    private final int tripDistance;
    private static final int DISTANCE_PER_LITER = 13;
    private static final String NAME = "K5";

    public K5(final int distance) {
        this.tripDistance = distance;
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

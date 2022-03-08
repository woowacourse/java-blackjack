package rentCompany.car;

public class K5 extends Car {
    private static final int DISTANCE_PER_LITER = 13;
    private static final String NAME = "K5";
    private final int tripDistance;

    public K5(final int distance) {
        this.tripDistance = distance;
    }

    @Override
    public double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    public double getTripDistance() {
        return tripDistance;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

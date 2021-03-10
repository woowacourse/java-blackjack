package car;

public class K5 extends Car {

    private static final String CAR_NAME = "K5";
    private static final int KILOMETER_PER_LITER = 13;
    private final int distance;

    public K5(final int distance) {
        this.distance = distance;
    }

    @Override
    double getDistancePerLiter() {
        return KILOMETER_PER_LITER;
    }

    @Override
    double getTripDistance() {
        return distance;
    }

    @Override
    String getName() {
        return CAR_NAME;
    }
}

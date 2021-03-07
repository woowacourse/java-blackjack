package car;

public class Sonata extends Car {

    private static final String CAR_NAME = "Sonata";
    private static final int KILOMETER_PER_LITER = 10;
    private final int distance;

    public Sonata(final int distance) {
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

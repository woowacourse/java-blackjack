package car;

public class Avante extends Car {

    private static final String CAR_NAME = "Avante";
    private static final int KILOMETER_PER_LITER = 15;
    private final int distance;

    public Avante(final int distance) {
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

package rentCompany.car;

public class Avante extends Car {
    private final int tripDistance;
    private static final int DISTANCE_PER_LITER = 15;
    private static final String NAME = "Avante";

    public Avante(final int distance) {
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
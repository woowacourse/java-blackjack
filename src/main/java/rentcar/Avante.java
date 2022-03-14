package rentcar;

public class Avante extends Car {

    private static final String NAME = "Avante";
    private static final double DISTANCE_PER_LITER = 15;

    private final int tripDistance;

    public Avante(final int tripDistance) {
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

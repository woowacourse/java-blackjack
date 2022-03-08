package rentCompany.car;

public class Avante extends Car {
    private static final int DISTANCE_PER_LITER = 15;
    private static final String NAME = "Avante";
    private final int tripDistance;

    public Avante(final int distance) {
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
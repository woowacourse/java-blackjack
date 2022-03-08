package rentCompany.car;

public class Sonata extends Car {
    private static final int DISTANCE_PER_LITER = 10;
    private static final String NAME = "Sonata";
    private final int tripDistance;

    public Sonata(final int distance) {
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

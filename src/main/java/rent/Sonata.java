package rent;

public class Sonata extends Car {

    private static final double DISTANCE_PER_LITER = 10;
    private static final String NAME = "Sonata";

    public Sonata(int distance) {
        super(distance);
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

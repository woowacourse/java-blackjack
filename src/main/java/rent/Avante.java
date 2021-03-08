package rent;

public class Avante extends Car {

    private static final double DISTANCE_PER_LITER = 15;
    private static final String NAME = "Avante";

    public Avante(int distance) {
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

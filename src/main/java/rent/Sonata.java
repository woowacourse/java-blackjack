package rent;

public class Sonata extends Car {

    private static final double DISTANCE_PER_LITER = 10;

    public Sonata(int distance) {
        super(distance);
    }

    @Override
    double getDistancePerLiter() {
        return 0;
    }

    @Override
    double getTripDistance() {
        return tripDistance;
    }

    @Override
    String getName() {
        return null;
    }
}

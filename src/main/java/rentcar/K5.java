package rentcar;

public class K5 extends Car{

    private static final String NAME = "K5";
    private static final double DISTANCE_PER_LITER = 13;
    private final double distance;

    public K5(double distance) {
        this.distance = distance;
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    double getTripDistance() {
        return distance;
    }

    @Override
    String getName() {
        return NAME;
    }
}

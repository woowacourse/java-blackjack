package fuel;

public class K5 extends Car{

    private final static int distancePerLiter = 13;
    private static final String NAME = "K5";

    private final double distance;

    public K5(final double distance) {
        this.distance = distance;
    }

    @Override
    public double getDistancePerLiter() {
        return distancePerLiter;
    }

    @Override
    public double getTripDistance() {
        return distance;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

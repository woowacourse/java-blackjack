package rent.car;

public class K5 extends Car {
    private static final String NAME = "K5";
    private static final double DISTANCE_PER_LITER = 13;

    public K5(double distance) {
        super(DISTANCE_PER_LITER, distance, NAME);
    }

    @Override
    public double getDistancePerLiter() {
        return distancePerLiter;
    }

    @Override
    public double getTripDistance() {
        return tripDistance;
    }

    @Override
    public String getName() {
        return name;
    }
}

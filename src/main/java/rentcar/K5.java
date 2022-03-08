package rentcar;

public class K5 extends Car {
    private static final double DISTANCE_PER_LITER = 13;

    private final int tripDistance;

    public K5(int tripDistance) {
        super(tripDistance);
        this.tripDistance = tripDistance;
    }

    @Override
    public double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    public double getTripDistance() {
        return tripDistance;
    }
}

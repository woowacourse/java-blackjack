package rentcar;

public class K5 extends Car {
    private static final double DISTANCE_PER_LITER = 13;
    private static final String NAME = "K5";

    public K5(int tripDistance) {
        super(tripDistance);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }
}

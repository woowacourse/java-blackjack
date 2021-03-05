package rentcar;

public class K5 extends Car {
    private static final int DISTANCE_PER_LITER = 13;
    private static final String K5_NAME = "K5";

    public K5(int distance) {
        super(distance);
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    String getName() {
        return K5_NAME;
    }
}

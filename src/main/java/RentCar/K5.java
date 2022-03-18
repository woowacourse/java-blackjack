package RentCar;

public class K5 extends Car {

    private static final double DISTANCE_PER_LITER = 13;
    private static final String NAME = "K5";

    public K5(double distance) {
        super(distance, NAME);
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }
}

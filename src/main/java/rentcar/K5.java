package rentcar;

public class K5 extends Car {
    private final int mileage = 13;

    public K5(final int distance) {
        super(distance);
    }

    @Override
    double getDistancePerLiter() {
        return mileage;
    }

    @Override
    double getTripDistance() {
        return distance;
    }
}

package rentcar;

public class Sonata extends Car {
    private final int mileage = 10;

    public Sonata(final int distance) {
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

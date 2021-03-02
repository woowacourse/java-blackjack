package rentcar;

public class Avante extends Car {
    private final int mileage = 15;

    public Avante(final int distance) {
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

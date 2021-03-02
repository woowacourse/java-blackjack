package rentcompany.abstractcompany;

public class K5 extends Car {

    private static final int mileage = 13;

    private final double distance;

    public K5(double distance) {
        this.distance = distance;
    }

    @Override
    double getDistancePerLiter() {
        return mileage;
    }

    @Override
    double getTripDistance() {
        return distance;
    }

    @Override
    String getName() {
        return getClass().getSimpleName();
    }
}

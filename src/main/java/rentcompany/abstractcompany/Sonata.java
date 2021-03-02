package rentcompany.abstractcompany;

public class Sonata extends Car {

    private static final int mileage = 10;

    private final double distance;

    public Sonata(double distance) {
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

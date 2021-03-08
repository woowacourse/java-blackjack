package rentcompany.abstractcompany;

public class Avante extends Car {

    private static final double mileage = 15;

    private final double distance;

    public Avante(double distance) {
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

package rentcompany.interfacecompany;

public class Sonata implements Car {

    private static final int mileage = 10;

    private final double distance;

    public Sonata(double distance) {
        this.distance = distance;
    }

    @Override
    public double getDistancePerLiter() {
        return mileage;
    }

    @Override
    public double getTripDistance() {
        return distance;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}

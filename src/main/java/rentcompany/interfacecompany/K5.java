package rentcompany.interfacecompany;

public class K5 implements Car {

    private static final int mileage = 13;

    private final double distance;

    public K5(double distance) {
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

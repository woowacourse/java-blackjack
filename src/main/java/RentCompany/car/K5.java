package RentCompany.car;

public class K5 extends Car {
    public K5(double distance) {
        super(distance);
    }

    @Override
    protected double getDistancePerLiter() {
        return 20.0;
    }

    @Override
    protected double getDistance() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}

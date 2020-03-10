package RentCompany.car;

public class Sonata extends Car {
    public Sonata(double distance) {

        super(distance);
    }

    @Override
    protected double getDistancePerLiter() {
        return 10.0;
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

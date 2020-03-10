package RentCompany.car;

public class Avante extends Car {
    public Avante(double distance) {
        super(distance);
    }

    @Override
    protected double getDistancePerLiter() {
        return 15.0;
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

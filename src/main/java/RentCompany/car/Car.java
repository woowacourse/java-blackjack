package RentCompany.car;

public abstract class Car {
    private final double distance;

    protected Car(double distance) {
        this.distance = distance;
    }

    protected abstract double getDistancePerLiter();

    protected abstract double getDistance();

    public abstract String getName();

    public double getChargeQuantity() {
        return getDistance() / getDistancePerLiter();
    }
}

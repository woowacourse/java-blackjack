package fuelInjection.car;

public abstract class Car {

    protected final double fuelEfficiency;
    protected final double tripDistance;
    protected final String name;

    public Car(final double fuelEfficiency, final double tripDistance, final String name) {
        this.fuelEfficiency = fuelEfficiency;
        this.tripDistance = tripDistance;
        this.name = name;
    }

    public abstract double getDistancePerLiter();

    public abstract double getTripDistance();

    public abstract String getName();

    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}
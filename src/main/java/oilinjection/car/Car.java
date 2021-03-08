package oilinjection.car;

public abstract class Car {

    abstract double getDistancePerLiter();

    abstract double getTripDistance();

    public abstract String getName();

    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

}
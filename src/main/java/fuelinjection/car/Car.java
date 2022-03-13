package fuelinjection.car;

public abstract class Car {

    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

    public abstract double getDistancePerLiter();

    public abstract double getTripDistance();

    public abstract String getName();

}

package fuelinjection;

public abstract class Car {
    int fuelEfficiency;
    String carName;
    int distance;

    abstract double getDistancePerLiter();

    abstract double getTripDistance();

    abstract String getName();

    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}
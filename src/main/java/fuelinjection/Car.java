package fuelinjection;

public abstract class Car {

    private final double tripDistance;

    public Car(double tripDistance) {
        this.tripDistance = tripDistance;
    }

    abstract double getDistancePerLiter();

    double getTripDistance() {
        return tripDistance;
    }

    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}
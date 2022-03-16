package fuelinjection;

abstract class Car {

    private final double tripDistance;

    Car(double tripDistance) {
        this.tripDistance = tripDistance;
    }

    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

    abstract String getName();

    abstract double getDistancePerLiter();

    double getTripDistance() {
        return tripDistance;
    }
}
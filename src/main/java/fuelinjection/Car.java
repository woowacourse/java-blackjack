package fuelinjection;

public abstract class Car {

    private final String name;
    private final double distance;

    Car(String name, double distance) {
        this.name = name;
        this.distance = distance;
    }

    abstract double getDistancePerLiter();

    String getName() {
        return this.name;
    }

    double getTripDistance() {
        return distance;
    }

    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}

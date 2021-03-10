package gasfueling;

public abstract class Car {

    private final double distance;

    public Car(double distance) {
        this.distance = distance;
    }

    abstract double getDistancePerLiter();

    public double getTripDistance() {
        return this.distance;
    }

    abstract String getName();

    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

}

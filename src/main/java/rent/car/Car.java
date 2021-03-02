package rent.car;

public abstract class Car {

    private double tripDistance;

    public Car(double tripDistance) {
        this.tripDistance = tripDistance;
    }

    public abstract double getDistancePerLiter();

    public double getTripDistance() {
        return tripDistance;
    }

    public abstract String getName();

    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}

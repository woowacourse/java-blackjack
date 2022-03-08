package rent.car;

public abstract class Car {

    private final double distance;

    public Car(double distance) {
        this.distance = distance;
    }

    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

    public abstract double getDistancePerLiter();

    public double getTripDistance() {
        return distance;
    };

    public abstract String getName();
}

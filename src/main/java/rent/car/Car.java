package rent.car;

public abstract class Car {

    private final double distance;

    public Car(double distance) {
        this.distance = distance;
    }

    public double getChargeQuantity() {
        return distance / getDistancePerLiter();
    }

    public abstract double getDistancePerLiter();

    public abstract String getName();
}

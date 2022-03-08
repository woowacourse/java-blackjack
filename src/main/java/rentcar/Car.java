package rentcar;

public abstract class Car {
    private final int tripDistance;

    public Car(int tripDistance) {
        this.tripDistance = tripDistance;
    }

    public abstract String getName();

    public abstract double getDistancePerLiter();

    public double getChargeQuantity() {
        return this.tripDistance / getDistancePerLiter();
    }
}

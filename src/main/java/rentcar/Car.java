package rentcar;

public abstract class Car {

    private final int distance;

    public Car(int distance) {
        this.distance = distance;
    }

    abstract double getDistancePerLiter();

    abstract String getName();

    double getChargeQuantity() {
        return distance / getDistancePerLiter();
    }
}

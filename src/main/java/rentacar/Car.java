package rentacar;

public abstract class Car {
    protected int distance;

    public Car(int distance) {
        this.distance = distance;
    }

    abstract double getDistancePerLiter();

    abstract double getTripDistance();

    abstract String getName();

    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}
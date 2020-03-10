package rentacar;

public abstract class Car implements Rentable {
    protected int distance;

    public Car(int distance) {
        this.distance = distance;
    }

    public abstract double getDistancePerLiter();

    public abstract double getTripDistance();

    public abstract String getName();

    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}
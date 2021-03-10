package fuel;

public abstract class Car {
    public final int tripDistance;

    public Car(int tripDistance) {
        this.tripDistance = tripDistance;
    }

    abstract double getDistancePerLiter();

    double getTripDistance() {
        return this.tripDistance;
    }

    abstract String getName();

    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}

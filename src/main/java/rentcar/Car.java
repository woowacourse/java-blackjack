package rentcar;

public abstract class Car {
    public abstract double getDistancePerLiter();

    public abstract double getTripDistance();

    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}

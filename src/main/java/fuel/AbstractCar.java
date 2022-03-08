package fuel;

public abstract class AbstractCar implements Car {

    protected double fuelEfficiency;
    protected double distance;

    public double getDistancePerLiter() {
        return this.fuelEfficiency;
    }

    public double getTripDistance() {
        return this.distance;
    }

    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}

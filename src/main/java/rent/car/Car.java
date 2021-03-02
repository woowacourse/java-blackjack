package rent.car;

public abstract class Car {

    protected double distancePerLiter;
    protected double tripDistance;
    protected String name;

    public Car(double distancePerLiter, double tripDistance, String name) {
        this.distancePerLiter = distancePerLiter;
        this.tripDistance = tripDistance;
        this.name = name;
    }

    public abstract double getDistancePerLiter();

    public abstract double getTripDistance();

    public abstract String getName();

    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}

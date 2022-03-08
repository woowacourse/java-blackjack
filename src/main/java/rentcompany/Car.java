package rentcompany;

public abstract class Car {

    protected final double distancePerLiter;
    protected final double tripDistance;
    protected final String name;

    public Car(int distancePerLiter, int tripDistance, String name) {
        this.distancePerLiter = distancePerLiter;
        this.tripDistance = tripDistance;
        this.name = name;
    }

    abstract double getDistancePerLiter();

    abstract double getTripDistance();

    abstract String getName();

    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}

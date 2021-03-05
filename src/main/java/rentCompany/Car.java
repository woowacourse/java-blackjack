package rentCompany;

public abstract class Car {

    protected String name;
    protected double tripDistance;

    public Car(double tripDistance) {
        this.tripDistance = tripDistance;
    }

    abstract double getDistancePerLiter();

    abstract double getTripDistance();

    abstract String getName();

    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}

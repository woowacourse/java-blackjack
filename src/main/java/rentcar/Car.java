package rentcar;

public abstract class Car {
    protected final double tripDistance;

    protected Car(final double tripDistance) {
        this.tripDistance = tripDistance;
    }

    abstract double getDistancePerLiter();

    abstract String getName();

    protected double getTripDistance() {
        return this.tripDistance;
    }

    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

    public String getSummary() {
        return getName() + " : " + Math.round(getChargeQuantity()) + "리터";
    }
}

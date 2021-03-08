package rent.car;

public abstract class Car implements CarInformation {

    private double tripDistance;

    public Car(double tripDistance) {
        this.tripDistance = tripDistance;
    }

    public double getTripDistance() {
        return tripDistance;
    }

    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}

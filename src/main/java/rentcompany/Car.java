package rentcompany;

public abstract class Car {

    private final int tripDistance;
    private final int fuelEfficiency;

    public Car(final int tripDistance, final int fuelEfficiency) {
        this.tripDistance = tripDistance;
        this.fuelEfficiency = fuelEfficiency;
    }

    public abstract String getName();

    public final double getChargeQuantity() {
        return tripDistance / fuelEfficiency;
    }
}


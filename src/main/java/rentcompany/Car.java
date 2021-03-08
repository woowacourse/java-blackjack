package rentcompany;

public abstract class Car {
    private final int fuelEfficiency;
    private final int moveDistance;

    Car(int moveDistance, int fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
        this.moveDistance = moveDistance;
    }

    public double calculateAmountOfFuel() {
        return (double) moveDistance / fuelEfficiency;
    }

    public abstract String getName();
}

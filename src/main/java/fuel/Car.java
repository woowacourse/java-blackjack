package fuel;

public abstract class Car {

    private final String name;
    private final int fuelNeeded;

    protected Car(final String name, final int fuelEfficiency, final int distance) {
        this.name = name;
        this.fuelNeeded = distance / fuelEfficiency;
    }

    public String getName() {
        return name;
    }

    public int getFuelNeeded() {
        return fuelNeeded;
    }
}

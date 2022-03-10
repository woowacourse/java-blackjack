package fuel;

public abstract class Car {

    private final String name;
    private final int fuelEfficiency;
    private final int distance;

    protected Car(final String name, final int fuelEfficiency, final int distance) {
        this.name = name;
        this.fuelEfficiency = fuelEfficiency;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public int getFuelNeeded() {
        return distance / fuelEfficiency;
    }
}

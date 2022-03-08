package fuel;

public class Sonata {
    private final String name = "Sonata";
    private final int fuelEfficiency = 10;
    private final int distance;

    public Sonata(int distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public int getFuelNeeded() {
        return distance / fuelEfficiency;
    }
}

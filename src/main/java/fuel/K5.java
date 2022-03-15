package fuel;

public class K5 implements Car {
    private final String name = "K5";
    private final int fuelEfficiency = 13;
    private final int distance;

    public K5(int distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public int getFuelNeeded() {
        return distance / fuelEfficiency;
    }
}

package fuel;

public class Avante implements Car {
    private final String name = "Avante";
    private final int fuelEfficiency = 15;
    private final int distance;

    public Avante(int distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public int getFuelNeeded() {
        return distance / fuelEfficiency;
    }
}

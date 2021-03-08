package fuel;

public class Avante implements Car {
    private final int amount;
    private static final int DISTANCE_PER_LITER = 15;
    private static final String NAME = "Avante";

    public Avante(int amount) {
        this.amount = amount;
    }

    @Override
    public double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    public double getTripDistance() {
        return amount;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

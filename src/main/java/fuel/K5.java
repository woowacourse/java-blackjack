package fuel;

public class K5 implements Car {
    private final int amount;
    private static final int DISTANCE_PER_LITER = 13;
    private static final String NAME = "K5";

    public K5(int amount) {
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

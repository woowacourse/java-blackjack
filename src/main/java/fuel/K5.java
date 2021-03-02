package fuel;

public class K5 implements Car {
    private int amount;

    public K5(int amount) {
        this.amount = amount;
    }

    @Override
    public double getDistancePerLiter() {
        return 0;
    }

    @Override
    public double getTripDistance() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}

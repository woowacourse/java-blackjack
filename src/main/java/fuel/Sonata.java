package fuel;

public class Sonata implements Car {
    private int amount;

    public Sonata(int amount) {
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

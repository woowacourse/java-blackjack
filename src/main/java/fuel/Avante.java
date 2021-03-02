package fuel;

public class Avante implements Car {
    private int amount;

    public Avante(int amount) {
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

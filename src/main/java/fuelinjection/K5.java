package fuelinjection;

public class K5 extends Car {
    private static final int EFFICIENCY = 13;
    private static final String NAME = "K5";

    @Override
    public void injectFuel(Distance distance) {
        this.fuel = distance.calculateRequiredFuel(EFFICIENCY);
    }

    @Override
    public String toString() {
        return NAME;
    }
}

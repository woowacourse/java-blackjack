package fuelinjection;

public class Avante extends Car {
    private static final int EFFICIENCY = 15;
    private static final String NAME = "Avante";

    @Override
    public void injectFuel(Distance distance) {
        this.fuel = distance.calculateRequiredFuel(EFFICIENCY);
    }

    @Override
    public String toString() {
        return NAME;
    }
}

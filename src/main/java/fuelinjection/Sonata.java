package fuelinjection;

public class Sonata extends Car {
    private static final int EFFICIENCY = 10;

    @Override
    public void injectFuel(Distance distance) {
        this.fuel = distance.calculateRequiredFuel(EFFICIENCY);
    }
}

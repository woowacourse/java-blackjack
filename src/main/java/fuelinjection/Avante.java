package fuelinjection;

public class Avante extends Car {
    public static final int EFFICIENCY = 15;

    @Override
    public void injectFuel(Distance distance) {
        this.fuel = distance.calculateRequiredFuel(EFFICIENCY);
    }
}

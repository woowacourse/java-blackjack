package fuelinjection;

public class Avante extends Car {
    public static final int EFFICIENCY = 15;

    @Override
    public void injectFuel(int distance) {
        this.fuel = distance / EFFICIENCY;
    }
}

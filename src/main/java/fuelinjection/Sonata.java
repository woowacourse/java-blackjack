package fuelinjection;

public class Sonata extends Car {
    public static final int EFFICIENCY = 10;

    @Override
    public void injectFuel(int distance) {
        this.fuel = distance / EFFICIENCY;
    }
}

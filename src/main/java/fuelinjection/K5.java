package fuelinjection;

public class K5 extends Car {
    public static final int EFFICIENCY = 13;

    @Override
    public void injectFuel(int distance) {
        this.fuel = distance / EFFICIENCY;
    }
}

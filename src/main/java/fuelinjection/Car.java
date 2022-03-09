package fuelinjection;

public abstract class Car implements CarInterface {
    protected int fuel;

    @Override
    public int getFuel() {
        return fuel;
    }
}

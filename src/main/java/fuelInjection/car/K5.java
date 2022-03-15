package fuelInjection.car;

public class K5 extends Car {

    private static final double FUEL = 13;
    private static final String NAME = "K5";

    public K5(final int distance) {
        super(FUEL, distance, NAME);
    }

    @Override
    public double getDistancePerLiter() {
        return FUEL;
    }

    @Override
    public double getTripDistance() {
        return this.tripDistance;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

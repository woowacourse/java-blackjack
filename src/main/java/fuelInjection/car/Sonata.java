package fuelInjection.car;

public class Sonata extends Car {

    private static final double FUEL = 10;
    private static final String NAME = "Sonata";

    public Sonata(final int distance) {
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

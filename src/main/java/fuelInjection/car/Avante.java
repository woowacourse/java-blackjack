package fuelInjection.car;

public class Avante extends Car {

    private static final double FUEL = 15;
    private static final String NAME = "Avante";

    public Avante(final int distance) {
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

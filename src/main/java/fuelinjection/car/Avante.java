package fuelinjection.car;

public class Avante extends Car {

    private static final double DISTANCE_PER_LITER = 15;
    private static final String NAME = "Avante";

    private final double tripDistance;

    public Avante(double tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    public double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    public double getTripDistance() {
        return this.tripDistance;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

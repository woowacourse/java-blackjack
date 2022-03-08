package fuelinjection;

public class Avante extends Car {

    private static final String NAME = "Avante";
    private static final double DISTANCE_PER_LITER = 15;

    private final double tripDistance;

    public Avante(double tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    double getTripDistance() {
        return this.tripDistance;
    }

    @Override
    String getName() {
        return NAME;
    }
}

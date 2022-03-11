package fuelinjection;

class Avante extends Car {

    private static final double DISTANCE_PER_LITTER = 15;
    private static final String NAME = "Avante";

    Avante(double tripDistance) {
        super(tripDistance);
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITTER;
    }

    @Override
    String getName() {
        return NAME;
    }
}

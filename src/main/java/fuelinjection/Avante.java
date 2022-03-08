package fuelinjection;

public class Avante extends Car {

    private static final double DISTANCE_PER_LITTER = 15;

    public Avante(double tripDistance) {
        super(tripDistance);
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITTER;
    }
}

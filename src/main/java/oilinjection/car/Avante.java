package oilinjection.car;

public class Avante extends Car {

    private static final int DISTANCE_PRE_LITER = 15;
    private static final String NAME = "Avante";

    private final double distance;

    public Avante(double distance) {
        this.distance = distance;
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PRE_LITER;
    }

    @Override
    double getTripDistance() {
        return distance;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

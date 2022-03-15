package fuelInjection;

public class Avante extends Car {

    private static final int FUEL_EFFICIENCY = 15;
    private static final String NAME = "Avante";

    private final double distance;

    public Avante(double distance) {
        this.distance = distance;
    }

    @Override
    public double getDistancePerLiter() {
        return FUEL_EFFICIENCY;
    }

    @Override
    public double getTripDistance() {
        return distance;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

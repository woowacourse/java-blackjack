package fuelinjection;

public class Sonata extends Car{

    private static final String NAME = "Sonata";
    private static final double DISTANCE_PER_LITER = 10;

    public Sonata(double distance) {
        super(NAME, distance);
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }
}

package rentcar;

public class Sonata extends Car {
    private static final double DISTANCE_PER_LITER = 10;
    private static final String NAME = "Sonata";

    public Sonata(int tripDistance) {
        super(tripDistance);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }
}

package rentcar;

public class Avante extends Car {
    private static final double DISTANCE_PER_LITER = 15;
    private static final String NAME = "Avante";

    public Avante(int tripDistance) {
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

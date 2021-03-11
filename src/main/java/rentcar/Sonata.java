package rentcar;

public class Sonata extends Car {
    private static final int DISTANCE_PER_LITER = 10;
    private static final String SONATA_NAME = "Sonata";

    public Sonata(int distance) {
        super(distance);
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    String getName() {
        return SONATA_NAME;
    }
}

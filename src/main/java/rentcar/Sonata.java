package rentcar;

public class Sonata extends Car {

    private static final String NAME = "Sonata";
    private static final double PER_LITER = 10;

    private final double distance;

    public Sonata(double distance) {
        this.distance = distance;
    }

    @Override
    double getDistancePerLiter() {
        return PER_LITER;
    }

    @Override
    double getTripDistance() {
        return distance;
    }

    @Override
    String getName() {
        return NAME;
    }
}

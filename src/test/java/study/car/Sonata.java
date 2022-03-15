package study.car;

public class Sonata extends Car {
    private static final String SONATA_NAME = "Sonata";
    private static final double SONATA_DISTANCE_PER_LITER = 10.0;

    private final double tripDistance;

    public Sonata(double tripDistance) {
        this.tripDistance = tripDistance;
        super.name = SONATA_NAME;
    }

    @Override
    double getDistancePerLiter() {
        return SONATA_DISTANCE_PER_LITER;
    }

    @Override
    double getTripDistance() {
        return this.tripDistance;
    }
}

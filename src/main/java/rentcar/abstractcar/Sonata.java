package rentcar.abstractcar;

public class Sonata extends Car {

    private final double tripDistance;

    public Sonata(final double tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    double getDistancePerLiter() {
        return 10;
    }

    @Override
    double getTripDistance() {
        return this.tripDistance;
    }
}

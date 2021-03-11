package rentcar.abstractcar;

public class K5 extends Car {

    private final double tripDistance;

    public K5(final double tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    double getDistancePerLiter() {
        return 13;
    }

    @Override
    double getTripDistance() {
        return this.tripDistance;
    }
}

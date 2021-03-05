package rentcar.interfacecar;

public class K5 implements Car {

    private final double tripDistance;

    public K5(final double tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    public double getDistancePerLiter() {
        return 13;
    }

    @Override
    public double getTripDistance() {
        return this.tripDistance;
    }
}

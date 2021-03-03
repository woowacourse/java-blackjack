package rentcar.interfacecar;

public class Sonata implements Car {

    private final double tripDistance;

    public Sonata(final double tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    public double getDistancePerLiter() {
        return 10;
    }

    @Override
    public double getTripDistance() {
        return this.tripDistance;
    }
}

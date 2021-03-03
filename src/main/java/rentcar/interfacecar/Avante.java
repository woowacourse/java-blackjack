package rentcar.interfacecar;

public class Avante implements Car {

    private final double tripDistance;

    public Avante(final double tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    public double getDistancePerLiter() {
        return 15;
    }

    @Override
    public double getTripDistance() {
        return this.tripDistance;
    }
}

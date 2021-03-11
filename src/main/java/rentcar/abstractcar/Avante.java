package rentcar.abstractcar;

public class Avante extends Car {

    private final double tripDistance;

    public Avante(final double tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    double getDistancePerLiter() {
        return 15;
    }

    @Override
    double getTripDistance() {
        return this.tripDistance;
    }
}

package study.car;

public class Avante extends Car {
    private static final String AVANTE_NAME = "Avante";
    private static final double AVANTE_DISTANCE_PER_LITER = 15.0;

    private final double tripDistance;

    public Avante(double tripDistance) {
        this.tripDistance = tripDistance;
        super.name = AVANTE_NAME;
    }

    @Override
    double getDistancePerLiter() {
        return AVANTE_DISTANCE_PER_LITER;
    }

    @Override
    double getTripDistance() {
        return this.tripDistance;
    }
}

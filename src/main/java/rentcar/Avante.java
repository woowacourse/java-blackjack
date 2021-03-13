package rentcar;

public class Avante extends Car{

    private static final String NAME = "Avante";
    private static final double DISTANCE_PER_LITER = 15;
    private final double distance;

    public Avante(double distance) {
        this.distance = distance;
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
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

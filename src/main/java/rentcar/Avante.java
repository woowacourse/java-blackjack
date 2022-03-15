package rentcar;

public class Avante extends Car {

    private static final String NAME = "Avante";
    private static final double PER_LITER = 15;

    private double distance;

    public Avante(double distance) {
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

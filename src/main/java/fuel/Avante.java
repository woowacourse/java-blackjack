package fuel;

public class Avante extends Car{

    private final static int distancePerLiter = 15;
    private static final String NAME = "Avante";

    private final double distance;

    public Avante(final double distance) {
        this.distance = distance;
    }

    @Override
    public double getDistancePerLiter() {
        return distancePerLiter;
    }

    @Override
    public double getTripDistance() {
        return distance;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

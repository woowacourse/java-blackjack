package fuel;

public class Sonata extends Car{

    private final static int distancePerLiter = 10;
    private static final String NAME = "Sonata";

    private final double distance;

    public Sonata(final double distance) {
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

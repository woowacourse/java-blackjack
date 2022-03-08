package rentcompany;

public class Sonata extends Car {

    private static final String NAME = "Sonata";
    private static final int DISTANCE_PER_LITER = 10;

    public Sonata(int distance) {
        super(distance);
    }

    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

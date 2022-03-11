package rentcompany;

public class Sonata extends Car{

    private static final double DISTANCE_PER_LITER = 10;
    private static final String CAR_NAME = "Sonata";

    private final int tripDistance;

    public Sonata(int tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    public double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    double getTripDistance() {
        return tripDistance;
    }

    @Override
    String getName() {
        return CAR_NAME;
    }

}

package rentcar;

public class Sonata extends Car{

    private static final int DISTANCE_PER_LITER = 10;
    private static final String CAR_NAME = "Sonata";

    public Sonata(int tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    double getDistancePerLiter() {
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

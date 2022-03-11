package rentcompany;

public class K5 extends Car{

    private static final double DISTANCE_PER_LITER = 13;
    private static final String CAR_NAME = "K5";

    private final int tripDistance;

    public K5(int tripDistance) {
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

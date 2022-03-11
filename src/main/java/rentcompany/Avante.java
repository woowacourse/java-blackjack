package rentcompany;

public class Avante extends Car{

    private static final double DISTANCE_PER_LITER = 15;
    private static final String CAR_NAME = "Avante";

    private final int tripDistance;

    public Avante(int tripDistance) {
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

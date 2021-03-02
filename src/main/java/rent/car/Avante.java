package rent.car;

public class Avante extends Car {
    private static final String NAME = "Avante";
    private static final double DISTANCE_PER_LITER = 15;

    public Avante(double distance) {
        super(DISTANCE_PER_LITER, distance, NAME);
    }

    @Override
    public double getDistancePerLiter() {
        return distancePerLiter;
    }

    @Override
    public double getTripDistance() {
        return tripDistance;
    }

    @Override
    public String getName() {
        return name;
    }
}

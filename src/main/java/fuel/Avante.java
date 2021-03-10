package fuel;

public class Avante extends Car {
    private static final double DISTANCE_PER_LITER = 15;
    private static final String CAR_NAME = "Avante";

    public Avante(int tripDistance) {
        super(tripDistance);
    }

    @Override
    public double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    public String getName() {
        return CAR_NAME;
    }
}

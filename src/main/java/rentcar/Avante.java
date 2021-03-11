package rentcar;

public class Avante extends Car {
    private static final int DISTANCE_PER_LITER = 15;
    private static final String AVANTE_NAME = "Avante";

    public Avante(int distance) {
        super(distance);
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    String getName() {
        return AVANTE_NAME;
    }
}

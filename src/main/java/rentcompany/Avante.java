package rentcompany;

public class Avante extends Car {

    private static final String NAME = "Avante";
    private static final int DISTANCE_PER_LITER = 15;

    public Avante(int distance) {
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

package rent.car;

public class Avante extends Car {

    private static final double DISTANCE_PER_LITER = 15;

    public Avante(double distance) {
        super(distance);
    }
    @Override
    public double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    public String getName() {
        return null;
    }
}

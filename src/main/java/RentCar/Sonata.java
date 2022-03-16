package RentCar;

public class Sonata extends Car {

    private static final double DISTANCE_PER_LITER = 10;
    private static final String NAME = "Sonata";

    public Sonata(double distance) {
        super(distance, NAME);
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }
}

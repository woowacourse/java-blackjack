package RentCar.Domain;

public class Avante extends Car {

    private static final String NAME = "Avante";
    private static final int DISTANCE_PER_LITER = 15;

    public Avante(int distance) {
        super(distance);
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    double getTripDistance() {
        return distance;
    }

    @Override
    String getName() {
        return NAME;
    }
}

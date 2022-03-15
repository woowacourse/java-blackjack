package fuel.domain;

public class Avante extends Car {

    private static final int distancePerLiter = 15;

    public Avante(final int distance) {
        super(distance);
    }

    @Override
    double getDistancePerLiter() {
        return distancePerLiter;
    }

    @Override
    double getTripDistance() {
        return distance;
    }

    @Override
    String getName() {
        return "Avante";
    }
}

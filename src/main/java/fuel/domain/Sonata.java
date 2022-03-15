package fuel.domain;

public class Sonata extends Car {

    private static final int distancePerLiter = 10;

    public Sonata(final int distance) {
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
        return "Sonata";
    }
}

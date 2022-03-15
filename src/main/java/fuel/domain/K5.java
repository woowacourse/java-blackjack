package fuel.domain;

public class K5 extends Car {

    private static final int distancePerLiter = 13;

    public K5(final int distance) {
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
        return "K5";
    }
}

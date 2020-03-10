package fuel.domain;

public class K5 extends Car {
    private final int distancePerLiter = 13;
    private final int tripDistance;
    private final String name = "K5";

    public K5(int distance) {
        this.tripDistance = distance;
    }

    @Override
    double getDistancePerLiter() {
        return distancePerLiter;
    }

    @Override
    double getTripDistance() {
        return tripDistance;
    }

    @Override
    String getName() {
        return name;
    }
}

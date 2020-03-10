package fuel.domain;

public class Sonata extends Car {
    private final int distancePerLiter = 10;
    private final int tripDistance;
    private final String name = "Sonata";

    public Sonata(int distance) {
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

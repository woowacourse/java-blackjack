package rentcar;

public class K5 extends Car {
    private final String name = "K5";
    private final double distancePerLiter = 13;
    private final TripDistance tripDistance;

    public K5(double tripDistance) {
        this.tripDistance = new TripDistance(tripDistance);
    }

    @Override
    double getDistancePerLiter() {
        return distancePerLiter;
    }

    @Override
    double getTripDistance() {
        return tripDistance.getDistance();
    }

    @Override
    String getName() {
        return name;
    }
}

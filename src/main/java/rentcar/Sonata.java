package rentcar;

public class Sonata extends Car {
    private final String name = "Sonata";
    private final double distancePerLiter = 10;
    private final TripDistance tripDistance;

    public Sonata(double tripDistance) {
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

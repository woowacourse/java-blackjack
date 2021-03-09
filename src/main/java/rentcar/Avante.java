package rentcar;

public class Avante extends Car {
    private final String name = "Avante";
    private final double distancePerLiter = 15;
    private final TripDistance tripDistance;

    public Avante(double tripDistance) {
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

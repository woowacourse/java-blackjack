package charge;

public class K5 extends Car {

    private final double distancePerLiter;
    private final double tripDistance;
    private final String name;

    public K5(double tripDistance) {
        this.distancePerLiter = 13;
        this.tripDistance = tripDistance;
        this.name = "K5";
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

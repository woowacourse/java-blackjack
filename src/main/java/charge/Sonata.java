package charge;

public class Sonata extends Car {

    private final double distancePerLiter;
    private final double tripDistance;
    private final String name;

    public Sonata(double tripDistance) {
        this.distancePerLiter = 10;
        this.tripDistance = tripDistance;
        this.name = "Sonata";
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

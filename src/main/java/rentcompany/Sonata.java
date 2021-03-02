package rentcompany;

public class Sonata extends Car {
    private static final String CAR_NAME = "Sonata";
    private static final double distancePerLiter = 10;

    public Sonata(int tripDistance) {
        super(tripDistance);
        this.name = CAR_NAME;
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
        return this.name;
    }
}

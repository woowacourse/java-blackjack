package rentcompany;

public class K5 extends Car {
    private static final String CAR_NAME = "K5";
    private static final double distancePerLiter = 13;

    public K5(int tripDistance) {
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

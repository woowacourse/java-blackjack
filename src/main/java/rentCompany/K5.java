package rentCompany;

public class K5 extends Car {

    private final static String name = "K5";

    public K5(int tripDistance) {
        super(tripDistance);
    }

    @Override
    double getDistancePerLiter() {
        return 13;
    }

    @Override
    double getTripDistance() {
        return super.tripDistance;
    }

    @Override
    String getName() {
        return this.name;
    }
}

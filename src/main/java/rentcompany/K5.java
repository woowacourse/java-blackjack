package rentcompany;

public class K5 implements Car {

    private final int tripDistance;

    public K5(int tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    public double getDistancePerLiter() {
        return 13;
    }

    @Override
    public double getTripDistance() {
        return tripDistance;
    }

    @Override
    public String getName() {
        return "K5";
    }
}

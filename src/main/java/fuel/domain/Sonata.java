package fuel.domain;

public class Sonata implements Car {
    private final int distancePerLiter = 10;
    private final int tripDistance;
    private final String name = "Sonata";

    public Sonata(int distance) {
        this.tripDistance = distance;
    }

    @Override
    public double getDistancePerLiter() {
        return distancePerLiter;
    }

    @Override
    public double getTripDistance() {
        return tripDistance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}

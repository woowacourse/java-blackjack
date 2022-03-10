package fuel;

public class Sonata {

    private final static int distancePerLiter = 10;
    private static final String NAME = "소나타";

    private final double distance;

    public Sonata(final double distance) {
        this.distance = distance;
    }


    public double getDistancePerLiter() {
        return distancePerLiter;
    }

    public double getTripDistance() {
        return distance;
    }

    public String getName() {
        return NAME;
    }

    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}

package fuel;

public class Sonata {

    private final static int distancePerLiter = 10;
    private static final String NAME = "소나타";

    private final int distance;

    public Sonata(final int distance) {
        this.distance = distance;
    }


    public int getDistancePerLiter() {
        return distancePerLiter;
    }

    public int getTripDistance() {
        return distance;
    }

    public String getName() {
        return NAME;
    }
}

package fuel;

public class Sonata {

    private final int distancePerLiter;
    private final int distance;

    public Sonata(final int distance) {
        distancePerLiter = 15;
        this.distance = distance;
    }


    public int getDistancePerLiter() {
        return distancePerLiter;
    }

    public int getTripDistance() {
        return distance;
    }
}

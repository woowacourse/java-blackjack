package rentacar;

public class Sonata implements Rentable {

    private static final int SONATA_DISTANCE_PER_LITER = 10;

    private int distance;

    public Sonata(int distance) {
        this.distance = distance;
    }

    @Override
    public double getDistancePerLiter() {
        return SONATA_DISTANCE_PER_LITER;
    }

    @Override
    public double getTripDistance() {
        return this.distance;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}

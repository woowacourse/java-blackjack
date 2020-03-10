package rentacar;

public class K5 implements Rentable {

    private static final int K5_DISTANCE_PER_LITER = 13;

    private int distance;

    public K5(int distance) {
        this.distance = distance;
    }

    @Override
    public double getDistancePerLiter() {
        return K5_DISTANCE_PER_LITER;
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

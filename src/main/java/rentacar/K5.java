package rentacar;

public class K5 extends Car implements Rentable {

    private static final int K5_DISTANCE_PER_LITER = 13;

    public K5(int distance) {
        super(distance);
    }

    @Override
    public double getDistancePerLiter() {
        return K5_DISTANCE_PER_LITER;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

    @Override
    public double getTripDistance() {
        return this.distance;
    }
}

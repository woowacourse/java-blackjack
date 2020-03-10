package rentacar;

public class K5 extends Car {

    private static final int K5_DISTANCE_PER_LITER = 13;

    public K5(int distance) {
        super(distance);
    }

    @Override
    double getDistancePerLiter() {
        return K5_DISTANCE_PER_LITER;
    }

    @Override
    double getTripDistance() {
        return this.distance;
    }

    @Override
    String getName() {
        return this.getClass().getSimpleName();
    }
}

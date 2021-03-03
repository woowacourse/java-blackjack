package rentcar;

public class K5 extends Car {

    private static final int DISTANCE_PER_LITER = 13;

    private final String name;
    private final double distance;

    public K5(double distance) {
        this.distance = distance;
        this.name = "K5";
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    double getTripDistance() {
        return distance;
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    double getChargeQuantity() {
        return super.getChargeQuantity();
    }
}

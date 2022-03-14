package fuel;

public class K5 extends Car {

    public static final int DISTANCE_PER_LITER_K5 = 13;
    public static final String CAR_NAME_K5 = "K5";

    public K5(int distance) {
        super(DISTANCE_PER_LITER_K5, distance, CAR_NAME_K5);
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITER_K5;
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
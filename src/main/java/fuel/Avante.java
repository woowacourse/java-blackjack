package fuel;

public class Avante extends Car {

    public static final int DISTANCE_PER_LITER_AVANTE = 15;
    public static final String CAR_NAME_AVANTE = "Avante";

    public Avante(int distance) {
        super(DISTANCE_PER_LITER_AVANTE, distance, CAR_NAME_AVANTE);
    }

    @Override
    double getDistancePerLiter() {
        return DISTANCE_PER_LITER_AVANTE;
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
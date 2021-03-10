package rentcar;

public class Avante extends Car {

    private static final int DISTANCE_PER_LITER = 15;

    private final String name;
    private final double distance;

    public Avante(double distance) {
        this.distance = distance;
        this.name = "Avante";
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

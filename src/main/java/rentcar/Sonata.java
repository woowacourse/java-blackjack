package rentcar;

public class Sonata extends Car {

    private static final int DISTANCE_PER_LITER = 10;

    private final String name;
    private final double distance;

    public Sonata(double distance) {
        this.distance = distance;
        this.name = "Sonata";
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

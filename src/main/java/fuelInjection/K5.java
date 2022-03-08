package fuelInjection;

public class K5 {

    private static final int FUEL_EFFICIENCY = 13;

    private final double distance;

    public K5(double distance) {
        this.distance = distance;
    }

    public double getChargeQuantity() {
        return distance / FUEL_EFFICIENCY;
    }
}

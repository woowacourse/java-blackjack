package fuelinjection.car;

public class K5 implements Car{
    private final int fuelEfficiency = 13;
    private final int distance;

    public K5(int distance) {
        this.distance = distance;
    }

    @Override
    public String getName() {
        return "K5";
    }

    @Override
    public double getChargeQuantity() {
        return (double) distance/fuelEfficiency;
    }
}

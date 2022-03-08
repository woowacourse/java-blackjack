package fuelinjection.car;

public class Sonata implements Car{
    private final int fuelEfficiency = 10;
    private final int distance;

    public Sonata(int distance) {
        this.distance = distance;
    }

    @Override
    public String getName() {
        return "Sonata";
    }

    @Override
    public double getChargeQuantity() {
        return (double) distance/fuelEfficiency;
    }
}

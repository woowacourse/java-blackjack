package fuelinjection.car;

public class Avante implements Car {
    private final int fuelEfficiency = 15;
    private final int distance;

    public Avante(int distance) {
        this.distance = distance;
    }

    @Override
    public String getName() {
        return "Avante";
    }

    @Override
    public double getChargeQuantity() {
        return (double) distance/fuelEfficiency;
    }
}

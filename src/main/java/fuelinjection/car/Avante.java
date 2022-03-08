package fuelinjection.car;

public class Avante extends Car {
    private int fuelEfficiency = 15;

    public Avante(int distance) {
        super(distance);
    }

    @Override
    double getDistancePerLiter() {
        return fuelEfficiency;
    }

    @Override
    double getTripDistance() {
        return super.distance;
    }

    @Override
    public String getName() {
        return "Avante";
    }
}

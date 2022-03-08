package fuelinjection.car;

public class Sonata extends Car{
    private int fuelEfficiency = 10;

    public Sonata(int distance) {
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
        return "Sonata";
    }
}

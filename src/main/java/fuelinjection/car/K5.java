package fuelinjection.car;

public class K5 extends Car{
    private int fuelEfficiency = 13;

    public K5(final int distance) {
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
        return "K5";
    }
}

package rantcompany;

public class Sonata extends Car {

    private final String name;
    private final int tripDistance;
    private final int fuelEfficiency;

    public Sonata(int tripDistance) {
        this.name = "Sonata";
        this.tripDistance = tripDistance;
        this.fuelEfficiency = 10;
    }

    @Override
    double getDistancePerLiter() {
        return fuelEfficiency;
    }

    @Override
    double getTripDistance() {
        return tripDistance;
    }

    @Override
    String getName() {
        return name;
    }
}

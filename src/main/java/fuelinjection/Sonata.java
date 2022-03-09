package fuelinjection;

public class Sonata extends Car {
    private final static String name = "Sonata";
    private final static double distancePerLiter = 10;

    private double tripDistance;

    public Sonata(double tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    double getDistancePerLiter() {
        return distancePerLiter;
    }

    @Override
    double getTripDistance() {
        return tripDistance;
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}

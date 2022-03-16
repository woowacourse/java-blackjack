package fuelinjection;

public class K5 extends Car {
    private final static String name = "K5";
    private final static double distancePerLiter = 13;

    private double tripDistance;

    public K5(double tripDistance) {
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

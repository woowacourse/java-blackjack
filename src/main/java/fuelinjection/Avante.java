package fuelinjection;

public class Avante extends Car {
    private final static String name = "Avante";
    private final static double distancePerLiter = 15;

    private double tripDistance;

    public Avante(double tripDistance) {
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

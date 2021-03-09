package fuelinjection;

public class Sonata extends Car {

    public Sonata(int distance) {
        super.distance = distance;
        super.fuelEfficiency = 10;
        super.carName = "Sonata";
    }

    @Override
    double getDistancePerLiter() {
        return fuelEfficiency;
    }

    @Override
    double getTripDistance() {
        return distance;
    }

    @Override
    String getName() {
        return carName;
    }
}

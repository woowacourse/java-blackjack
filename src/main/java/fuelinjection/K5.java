package fuelinjection;

public class K5 extends Car {

    public K5(int distance) {
        super.distance = distance;
        super.fuelEfficiency = 13;
        super.carName = "K5";
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

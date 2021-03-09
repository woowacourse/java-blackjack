package fuelinjection;

public class Avante extends Car {

    public Avante(int distance) {
        super.distance = distance;
        super.fuelEfficiency = 15;
        super.carName = "Avante";
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

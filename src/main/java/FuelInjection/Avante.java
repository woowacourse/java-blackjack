package FuelInjection;

public class Avante extends Car {

    private String name;
    private double distance;
    private double distancePerLiter;

    public Avante(int distance) {
        this.name = "Avante";
        this.distance = distance;
        distancePerLiter = 15;
    }

    @Override
    double getDistancePerLiter() {
        return distancePerLiter;
    }

    @Override
    double getTripDistance() {
        return distance;
    }

    @Override
    String getName() {
        return name;
    }
}

package FuelInjection;

public class K5 extends Car {

    private String name;
    private double distance;
    private double distancePerLiter;


    public K5(int distance) {
        this.name = "K5";
        this.distance = distance;
        this.distancePerLiter = 13;
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

package FuelInjection;

public class Sonata extends Car{

    private String name;
    private double distance;
    private double distancePerLiter;

    public Sonata(int distance) {
        this.name = "Sonata";
        this.distance = distance;
        this.distancePerLiter = 10;
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

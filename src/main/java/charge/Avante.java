package charge;

public class Avante extends Car {

    private final double distancePerLiter;
    private final double tripDistance;
    private final String name;

    public Avante(double tripDistance) {
        this.distancePerLiter = 15;
        this.tripDistance = tripDistance;
        this.name = "Avante";
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
}

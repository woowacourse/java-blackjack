package fuel.domain;

public class Avante extends Car {
    private final int distancePerLiter = 15;
    private final int tripDistance;
    private final String name = "Avante";

    public Avante(int distance) {
        this.tripDistance = distance;
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

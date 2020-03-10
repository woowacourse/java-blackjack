package fuel.domain;

public class Avante implements Car2 {
    private final int distancePerLiter = 15;
    private final int tripDistance;
    private final String name = "Avante";

    public Avante(int distance) {
        this.tripDistance = distance;
    }

    @Override
    public double getDistancePerLiter() {
        return distancePerLiter;
    }

    @Override
    public double getTripDistance() {
        return tripDistance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}

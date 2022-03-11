package fuel;

public abstract class AbstractCar implements Car {

    private final double distancePerLiter;
    private final double tripDistance;
    private final String name;

    public AbstractCar(double distancePerLiter, double tripDistance, String name) {
        this.distancePerLiter = distancePerLiter;
        this.tripDistance = tripDistance;
        this.name = name;
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
}

package rentcompany;

public abstract class AbstractCar implements Car {
    private final String name;
    private final int distancePerLiter;
    private final int tripDistance;

    public AbstractCar(String name, int distancePerLiter, int tripDistance) {
        this.name = name;
        this.distancePerLiter = distancePerLiter;
        this.tripDistance = tripDistance;
    }

    @Override
    public String getName() {
        return name;
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
    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

    @Override
    public String generateReport() {
        return getName() + " : " + (int) getChargeQuantity() + "리터" + NEW_LINE;
    }
}
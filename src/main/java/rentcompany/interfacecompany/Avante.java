package rentcompany.interfacecompany;

public class Avante implements Car {

    private static final int mileage = 15;

    private final double distance;

    public Avante(double distance) {
        this.distance = distance;
    }

    @Override
    public double getDistancePerLiter() {
        return mileage;
    }

    @Override
    public double getTripDistance() {
        return distance;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}

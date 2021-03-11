package rentcompany;

public class Avante extends Car {
    private static final String CAR_NAME = "Avante";
    private static final double distancePerLiter = 15;

    public Avante(int tripDistance) {
        super(tripDistance);
        this.name = CAR_NAME;
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
        return this.name;
    }
}

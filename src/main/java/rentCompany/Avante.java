package rentCompany;

public class Avante extends Car {

    private final static String name = "Avante";

    public Avante(int tripDistance) {
        super(tripDistance);
    }

    @Override
    double getDistancePerLiter() {
        return 15;
    }

    @Override
    double getTripDistance() {
        return super.tripDistance;
    }

    @Override
    String getName() {
        return this.name;
    }
}
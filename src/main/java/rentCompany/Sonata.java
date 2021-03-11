package rentCompany;

public class Sonata extends Car {

    private final static String name = "Sonata";

    public Sonata(int tripDistance) {
        super(tripDistance);
    }

    @Override
    double getDistancePerLiter() {
        return 10;
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

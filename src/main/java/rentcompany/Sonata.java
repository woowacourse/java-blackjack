package rentcompany;

public class Sonata extends Car {

    private static final int FUEL_EFFICIENCY = 10;

    public Sonata(int tripDistance) {
        super(tripDistance, FUEL_EFFICIENCY);
    }

    @Override
    String getName() {
        return null;
    }
}

package rentcompany;

public class Sonata extends Car {

    private static final int FUEL_EFFICIENCY = 10;
    private static final String NAME = "SONATA";

    public Sonata(final int tripDistance) {
        super(tripDistance, FUEL_EFFICIENCY);
    }

    @Override
    public String getName() {
        return NAME;
    }
}

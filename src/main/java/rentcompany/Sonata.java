package rentcompany;

public class Sonata extends Car {

    private static final int FUEL_EFFICIENCY = 10;
    private static final String NAME = "SONATA";

    public Sonata(int tripDistance) {
        super(tripDistance, FUEL_EFFICIENCY);
    }

    @Override
    String getName() {
        return NAME;
    }
}

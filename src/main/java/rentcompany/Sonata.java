package rentcompany;

public class Sonata extends Car {
    private static final int FUEL_EFFICIENCY = 10;
    private static final String NAME = "Sonata";

    public Sonata(int i) {
        super(i, FUEL_EFFICIENCY);
    }

    @Override
    public String getName() {
        return NAME;
    }
}

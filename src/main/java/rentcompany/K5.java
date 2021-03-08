package rentcompany;

public class K5 extends Car {
    private static final int FUEL_EFFICIENCY = 13;
    private static final String NAME = "K5";

    public K5(int i) {
        super(i, FUEL_EFFICIENCY);
    }

    @Override
    public String getName() {
        return NAME;
    }
}

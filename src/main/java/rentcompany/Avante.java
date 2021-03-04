package rentcompany;

public class Avante extends Car {
    private static final int FUEL_EFFICIENCY = 15;
    private static final String NAME = "Avante";

    public Avante(int i) {
        super(i, FUEL_EFFICIENCY);
    }

    @Override
    public String getName() {
        return NAME;
    }
}

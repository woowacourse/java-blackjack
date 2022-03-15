package rentcompany;

public class Avante extends Car {

    private static final int FUEL_EFFICIENCY = 15;
    private static final String NAME = "AVANTE";

    public Avante(int tripDistance) {
        super(tripDistance, FUEL_EFFICIENCY);
    }

    @Override
    String getName() {
        return NAME;
    }
}

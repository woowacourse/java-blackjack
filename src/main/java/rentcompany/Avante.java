package rentcompany;

public class Avante extends Car {

    private static final int FUEL_EFFICIENCY = 15;

    public Avante(int tripDistance) {
        super(tripDistance, FUEL_EFFICIENCY);
    }

    @Override
    String getName() {
        return null;
    }
}

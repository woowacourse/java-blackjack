package fuelinjection.fuelinjectionheritance;

public class Avante extends Car {
    private static final String NAME = "Avante";
    private static final int FUEL_EFFICIENCY = 15;

    public Avante(int moveDistance) {
        super(moveDistance);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getFuelEfficiency() {
        return FUEL_EFFICIENCY;
    }

    @Override
    public int getRequiredFuel() {
        return getMoveDistance() / FUEL_EFFICIENCY;
    }
}

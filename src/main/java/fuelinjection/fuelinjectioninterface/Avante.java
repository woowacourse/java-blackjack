package fuelinjection.fuelinjectioninterface;

public class Avante implements Car {
    private static final String NAME = "Avante";
    private static final int FUEL_EFFICIENCY = 15;

    private final int moveDistance;

    public Avante(int moveDistance) {
        this.moveDistance = moveDistance;
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
        return moveDistance / FUEL_EFFICIENCY;
    }
}

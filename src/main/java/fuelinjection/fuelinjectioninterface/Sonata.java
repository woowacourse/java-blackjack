package fuelinjection.fuelinjectioninterface;

public class Sonata implements Car {
    private static final String NAME = "Sonata";
    private static final int FUEL_EFFICIENCY = 10;

    private final int moveDistance;

    public Sonata(int moveDistance) {
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

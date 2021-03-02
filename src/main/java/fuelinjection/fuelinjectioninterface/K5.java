package fuelinjection.fuelinjectioninterface;

public class K5 implements Car {
    private static final String NAME = "K5";
    private static final int FUEL_EFFICIENCY = 13;

    private final int moveDistance;

    public K5(int moveDistance) {
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

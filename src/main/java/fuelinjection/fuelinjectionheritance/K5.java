package fuelinjection.fuelinjectionheritance;

public class K5 extends Car {
    private static final String NAME = "K5";
    private static final int FUEL_EFFICIENCY = 13;

    public K5(int moveDistance) {
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

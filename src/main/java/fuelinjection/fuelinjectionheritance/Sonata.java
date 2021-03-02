package fuelinjection.fuelinjectionheritance;

public class Sonata extends Car {
    private static final String NAME = "Sonata";
    private static final int FUEL_EFFICIENCY = 10;

    public Sonata(int moveDistance) {
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

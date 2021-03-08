package fuelinjection.fuelinjectionheritance;

public abstract class Car {

    private final int moveDistance;

    public Car(int moveDistance) {
        this.moveDistance = moveDistance;
    }

    abstract String getName();

    abstract int getFuelEfficiency();

    abstract int getRequiredFuel();

    protected int getMoveDistance() {
        return moveDistance;
    }
}

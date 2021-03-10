package fuel_injection;

public abstract class Car {

    protected final String name;
    protected final int efficiency;
    protected final int distance;

    public Car(String name, int efficiency, int distance) {
        this.name = name;
        this.efficiency = efficiency;
        this.distance = distance;
    }

    abstract double getDistancePerLiter();

    abstract double getTripDistance();

    public int getChargeQuantity() {
        return (int) (getTripDistance() / getDistancePerLiter());
    }

    abstract String getName();
}

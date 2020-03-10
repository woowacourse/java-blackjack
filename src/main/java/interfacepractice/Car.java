package interfacepractice;

public abstract class Car {

    private int fuelEfficiency;
    private int distance;

    public Car(int fuelEfficiency, int distance) {
        this.fuelEfficiency = fuelEfficiency;
        this.distance = distance;
    }

    public int getNeededFuel() {
        return distance / fuelEfficiency;
    }

    public abstract String getName();
}

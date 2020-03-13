package RentCar.Domain;

public class Car {

    protected final String name;
    protected final int distance;
    protected final int fuelEfficiency;

    public Car(String name, int fuelEfficiency, int distance) {
        this.name = name;
        this.fuelEfficiency = fuelEfficiency;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public int getAmountOfOil() {
        return distance / fuelEfficiency;
    }
}

package rentacar;

public abstract class Car {
    protected int distance;

    public Car(int distance) {
        this.distance = distance;
    }

    public abstract double getDistancePerLiter();

    public abstract String getName();

}
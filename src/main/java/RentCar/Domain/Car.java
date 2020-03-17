package RentCar.Domain;

public abstract class Car implements Transportation {

    protected final double distance;

    public Car(int distance) {
        this.distance = distance;
    }

    /**
     * 주입해야할 연료량을 구한다.
     */
    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}

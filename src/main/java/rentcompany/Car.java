package rentcompany;

public abstract class Car {

    private final int tripDistance;
    private final int fuelEfficiency;

    public Car(int tripDistance, int fuelEfficiency) {
        this.tripDistance = tripDistance;
        this.fuelEfficiency = fuelEfficiency;
    }

    /**
     * 차종의 이름
     */
    abstract String getName();

    /**
     * 주입해야할 연료량을 구한다.
     */
    double getChargeQuantity() {
        return tripDistance / fuelEfficiency;
    }
}


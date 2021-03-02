package rentcar;

public abstract class Car {

    private final int distance;

    public Car(int distance) {
        this.distance = distance;
    }

    /**
     * 리터당 이동 거리. 즉, 연비
     */
    abstract double getDistancePerLiter();

    /**
     * 차종의 이름
     */
    abstract String getName();

    /**
     * 주입해야할 연료량을 구한다.
     */
    double getChargeQuantity() {
        return distance / getDistancePerLiter();
    }
}

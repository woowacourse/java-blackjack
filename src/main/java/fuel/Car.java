package fuel;

public abstract class Car {

    protected final int distancePerLiter;
    protected final int distance;
    protected final String name;

    public Car(int distancePerLiter, int distance, String name) {
        this.distancePerLiter = distancePerLiter;
        this.distance = distance;
        this.name = name;
    }

    /**
     * 리터당 이동 거리. 즉, 연비
     */
    abstract double getDistancePerLiter();

    /**
     * 여행하려는 거리
     */
    abstract double getTripDistance();

    /**
     * 차종의 이름
     */
    abstract String getName();

    /**
     * 주입해야할 연료량을 구한다.
     */
    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}
package rentcar;

public abstract class Car {
    protected final int distance;

    public Car(final int distance) {
        this.distance = distance;
    }

    // 리터당 이동 거리. 즉, 연비
    abstract double getDistancePerLiter();

    // 여행하려는 거리
    abstract double getTripDistance();

    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

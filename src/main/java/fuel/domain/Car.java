package fuel.domain;

public abstract class Car {

    protected final int distance;

    protected Car(final int distance) {
        validateDistance(distance);
        this.distance = distance;
    }

    abstract double getDistancePerLiter();

    abstract double getTripDistance();

    abstract String getName();

    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

    private void validateDistance(final int distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
        }
    }
}

package rentcar;

public abstract class Car {
    private static final int MINIMUM_TRIP_DISTANCE = 0;

    public Car(int tripDistance) {
        validateTripDistance(tripDistance);
    }

    public abstract double getDistancePerLiter();

    public abstract double getTripDistance();

    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

    private void validateTripDistance(int tripDistance) {
        if (tripDistance <= MINIMUM_TRIP_DISTANCE) {
            throw new IllegalArgumentException("주행 거리가 0 이하일 수 없습니다.");
        }
    }
}

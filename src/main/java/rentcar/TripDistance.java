package rentcar;

public class TripDistance {
    private final double distance;

    public TripDistance(double distance) {
        validateDistance(distance);
        this.distance = distance;
    }

    private void validateDistance(double distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("주행 거리는 음수가 되면 안됩니다.");
        }
    }

    public double getDistance() {
        return distance;
    }
}

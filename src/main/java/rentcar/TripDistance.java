package rentcar;

public class TripDistance {
    public static final String DISTANCE_NON_NEGATIVE_ERROR = "주행 거리는 음수가 되면 안됩니다.";
    private final double distance;

    public TripDistance(double distance) {
        validateDistance(distance);
        this.distance = distance;
    }

    private void validateDistance(double distance) {
        if (distance < 0) {
            throw new IllegalArgumentException(DISTANCE_NON_NEGATIVE_ERROR);
        }
    }

    public double getDistance() {
        return distance;
    }
}

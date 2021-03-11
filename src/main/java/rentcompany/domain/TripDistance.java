package rentcompany.domain;

import java.util.Objects;

public class TripDistance {
    private static final String NEGATIVE_TRIP_DISTANCE_ERROR_MSG_FORMAT = "여행거리는 음수일 수 없습니다. 여행거리: %f";
    private double tripDistance;

    public TripDistance(double tripDistance) {
        if (tripDistance < 0) {
            throw new IllegalArgumentException(String.format(NEGATIVE_TRIP_DISTANCE_ERROR_MSG_FORMAT, tripDistance));
        }
        this.tripDistance = tripDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripDistance that = (TripDistance) o;
        return tripDistance == that.tripDistance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripDistance);
    }
}

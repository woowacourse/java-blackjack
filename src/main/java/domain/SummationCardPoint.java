package domain;

import java.util.List;

public record SummationCardPoint(int summationCardPoint) {

    public static SummationCardPoint of(List<CardPoint> cardPoints) {
        int summationCardPoint = cardPoints.stream()
                .mapToInt(CardPoint::point)
                .sum();
        return new SummationCardPoint(summationCardPoint);
    }

    public boolean isBiggerThan(SummationCardPoint other) {
        int otherPoint = other.summationCardPoint();
        return summationCardPoint > otherPoint;
    }
}

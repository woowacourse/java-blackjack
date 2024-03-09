package domain.blackjack;

import java.util.List;

public record SummationCardPoint(int summationCardPoint) {
    private static final int DEAD_POINT_THRESHOLD = 21;
    private static final int ADDITIONAL_CARD_THRESHOLD = 16;

    static SummationCardPoint of(List<CardPoint> cardPoints) {
        int summationCardPoint = cardPoints.stream()
                .mapToInt(CardPoint::point)
                .sum();
        return new SummationCardPoint(summationCardPoint);
    }

    public boolean isBiggerThan(SummationCardPoint other) {
        int otherPoint = other.summationCardPoint();
        return summationCardPoint > otherPoint;
    }

    public boolean isDeadPoint() {
        return this.isBiggerThan(new SummationCardPoint(DEAD_POINT_THRESHOLD));
    }

    public boolean isDealerAdditionalCardPoint() {
        return this.isBiggerThan(new SummationCardPoint(ADDITIONAL_CARD_THRESHOLD));
    }
}

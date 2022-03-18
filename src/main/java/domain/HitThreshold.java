package domain;

public enum HitThreshold {
    PLAYER_THRESHOLD(20),
    DEALER_THRESHOLD(16);

    private final int thresholdValue;

    HitThreshold(final int thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public boolean canHit(final int other) {
        return thresholdValue >= other;
    }
}

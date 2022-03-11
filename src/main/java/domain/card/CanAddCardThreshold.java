package domain.card;

public enum CanAddCardThreshold {
    PLAYER_THRESHOLD(20),
    DEALER_THRESHOLD(16);

    private final int thresholdValue;

    CanAddCardThreshold(int thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public boolean canAdd(int other) {
        return thresholdValue >= other;
    }
}

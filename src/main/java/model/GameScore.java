package model;

public record GameScore(int value) {
    private static final GameScore BLACKJACK_SCORE = new GameScore(21);
    private static final GameScore ADDITIONAL_ACE_SCORE = new GameScore(10);
    private static final GameScore DEALER_STAY_THRESHOLD = new GameScore(17);

    public GameScore withAce() {
        GameScore maximumScore = this.add(ADDITIONAL_ACE_SCORE);
        if (maximumScore.isGreaterThan(BLACKJACK_SCORE)) {
            return this;
        }
        return maximumScore;
    }

    public boolean isBust() {
        return this.isGreaterThan(BLACKJACK_SCORE);
    }

    public boolean isBlackJack() {
        return equals(BLACKJACK_SCORE);
    }

    public boolean doesDealerNeedCard() {
        return DEALER_STAY_THRESHOLD.isGreaterThan(this);
    }

    public boolean isGreaterThan(GameScore other) {
        return value > other.value;
    }

    public GameScore add(GameScore other) {
        return new GameScore(this.value + other.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

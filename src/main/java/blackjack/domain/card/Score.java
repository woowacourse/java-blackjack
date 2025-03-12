package blackjack.domain.card;

public record Score(int value) {
    private static final Score BLACKJACK_SCORE = new Score(21);
    private static final Score ADDITIONAL_SCORE = new Score(10);
    private static final Score DEALER_SCORE_THRESHOLD = new Score(17);

    public Score withAce() {
        Score maxScore = this.add(ADDITIONAL_SCORE);
        if (maxScore.isGreaterThan(BLACKJACK_SCORE)) {
            return this;
        }
        return maxScore;
    }

    private Score add(Score otherScore) {
        return new Score(this.value + otherScore.value);
    }

    public boolean isBlackjackScore() {
        return equals(BLACKJACK_SCORE);
    }

    public boolean isBust() {
        return this.isGreaterThan(BLACKJACK_SCORE);
    }

    public boolean isGreaterThan(Score otherScore) {
        return this.value > otherScore.value;
    }

    public boolean canTake() {
        return this.isLessThan(BLACKJACK_SCORE);
    }

    public boolean doesNeedDealerPickAdditionalCard() {
        return this.isLessThan(DEALER_SCORE_THRESHOLD);
    }

    public boolean isLessThan(Score otherScore) {
        return this.value <= otherScore.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

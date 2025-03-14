package blackjack.domain.card;

public record BlackjackScore(int value, int cardSize) {
    private static final int BLACKJACK_CARD_NUMBER = 2;
    private static final BlackjackScore BLACKJACK_BLACKJACK_SCORE = new BlackjackScore(21, 2);
    private static final BlackjackScore ADDITIONAL_BLACKJACK_SCORE = new BlackjackScore(10, Integer.MAX_VALUE);
    private static final BlackjackScore DEALER_BLACKJACK_SCORE_THRESHOLD = new BlackjackScore(17, Integer.MAX_VALUE);

    public BlackjackScore withAce() {
        BlackjackScore maxBlackjackScore = this.add(ADDITIONAL_BLACKJACK_SCORE);
        if (maxBlackjackScore.isGreaterThan(BLACKJACK_BLACKJACK_SCORE)) {
            return this;
        }
        return maxBlackjackScore;
    }

    private BlackjackScore add(BlackjackScore otherBlackjackScore) {
        return new BlackjackScore(this.value + otherBlackjackScore.value, this.cardSize);
    }

    public boolean isBlackjack() {
        return equals(BLACKJACK_BLACKJACK_SCORE) && this.cardSize == BLACKJACK_CARD_NUMBER;
    }

    public boolean isBust() {
        return this.isGreaterThan(BLACKJACK_BLACKJACK_SCORE);
    }

    public boolean isGreaterThan(BlackjackScore otherBlackjackScore) {
        return this.value > otherBlackjackScore.value;
    }

    public boolean canTake() {
        return this.isLessThan(BLACKJACK_BLACKJACK_SCORE);
    }

    public boolean doesNeedDealerPickAdditionalCard() {
        return this.isLessThan(DEALER_BLACKJACK_SCORE_THRESHOLD);
    }

    public boolean isLessThan(BlackjackScore otherBlackjackScore) {
        return this.value < otherBlackjackScore.value;
    }

    public WinningResult decide(BlackjackScore subBlackjackScore) {
        if (isWinning(subBlackjackScore)) {
            return WinningResult.WIN;
        }
        if (isLosing(subBlackjackScore)) {
            return WinningResult.LOSE;
        }
        return WinningResult.DRAW;
    }

    public boolean isWinning(BlackjackScore subBlackjackScore) {
        if (!this.isBust() && subBlackjackScore.isBust()) {
            return true;
        }
        if (this.isBlackjack() && !subBlackjackScore.isBlackjack()) {
            return true;
        }
        if (!this.isBust()) {
            return this.isGreaterThan(subBlackjackScore);
        }
        return false;
    }

    public boolean isLosing(BlackjackScore subBlackjackScore) {
        if (this.isBust() && !subBlackjackScore.isBust()) {
            return true;
        }
        if (!this.isBlackjack() && subBlackjackScore.isBlackjack()) {
            return true;
        }
        if (!this.isBust()) {
            return this.isLessThan(subBlackjackScore);
        }
        return false;
    }

    public boolean isDrawing(BlackjackScore subBlackjackScore) {
        if (this.isBust() && subBlackjackScore.isBust()) {
            return true;
        }

        if (this.isBlackjack() && !subBlackjackScore.isBlackjack()) {
            return false;
        }
        if (!this.isBlackjack() && subBlackjackScore.isBlackjack()) {
            return false;
        }

        return this.value == subBlackjackScore.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

package blackjack.domain.card;

public record BlackjackScore(int value, int cardSize) {
    private static final int BLACKJACK_CARD_NUMBER = 2;
    private static final int ADDITIONAL_BLACKJACK_SCORE = 10;
    private static final int DEALER_BLACKJACK_SCORE_THRESHOLD = 17;
    private static final int BLACKJACK_SCORE = 21;
    private static final BlackjackScore BLACKJACK_BLACKJACK_SCORE = new BlackjackScore(21, 2);

    public BlackjackScore withAce() {
        BlackjackScore maxBlackjackScore = this.addBlackjackCard();
        if (maxBlackjackScore.isGreaterThan(BLACKJACK_SCORE)) {
            return this;
        }
        return maxBlackjackScore;
    }

    private BlackjackScore addBlackjackCard() {
        return new BlackjackScore(this.value + ADDITIONAL_BLACKJACK_SCORE, this.cardSize);
    }

    public WinningResult decide(BlackjackScore subBlackjackScore) {
        if (isBlackjackWinning(subBlackjackScore)) {
            return WinningResult.BLACKJACK_WIN;
        }
        if (isWinning(subBlackjackScore)) {
            return WinningResult.WIN;
        }
        if (isLosing(subBlackjackScore)) {
            return WinningResult.LOSE;
        }
        return WinningResult.DRAW;
    }

    public boolean isBlackjackWinning(BlackjackScore subBlackjackScore) {
        if (this.isBlackjack() && !subBlackjackScore.isBlackjack()) {
            return true;
        }
        return false;
    }

    public boolean isWinning(BlackjackScore subBlackjackScore) {
        if (!this.isBust() && subBlackjackScore.isBust()) {
            return true;
        }
        if (!this.isBust()) {
            return this.isGreaterThan(subBlackjackScore.value);
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
            return this.isLessThan(subBlackjackScore.value);
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

    public boolean isBlackjack() {
        return equals(BLACKJACK_BLACKJACK_SCORE) && this.cardSize == BLACKJACK_CARD_NUMBER;
    }

    public boolean isBust() {
        return this.isGreaterThan(BLACKJACK_SCORE);
    }

    public boolean isGreaterThan(int otherBlackjackScore) {
        return this.value > otherBlackjackScore;
    }

    public boolean isLessThan(int otherBlackjackScore) {
        return this.value < otherBlackjackScore;
    }

    public boolean doesNeedDealerPickAdditionalCard() {
        return this.isLessThan(DEALER_BLACKJACK_SCORE_THRESHOLD);
    }

    public boolean canTake() {
        return this.isLessThan(BLACKJACK_SCORE);
    }

    public String getValue() {
        return String.valueOf(value);
    }
}

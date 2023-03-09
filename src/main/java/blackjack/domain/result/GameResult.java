package blackjack.domain.result;

public enum GameResult {
    BLACKJACK_WIN,
    NORMAL_WIN,
    TIE,
    LOSE;

    public GameResult reverseWinningStatus() {
        if (this.equals(NORMAL_WIN) || this.equals(BLACKJACK_WIN)) {
            return LOSE;
        }
        if (this.equals(LOSE)) {
            return GameResult.NORMAL_WIN;
        }
        return this;
    }
}

package blackjack.domain.result;

public enum GameResult {
    BLACKJACK_WIN(1.5),
    NORMAL_WIN(1.0),
    TIE(0.0),
    LOSE(-1.0);

    private final double profitRate;

    GameResult(final double profitRate) {
        this.profitRate = profitRate;
    }

    public int calculateProfit(int betAmount) {
        return (int) (betAmount * profitRate);
    }

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

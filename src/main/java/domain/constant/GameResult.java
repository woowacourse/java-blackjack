package domain.constant;

public enum GameResult {
    WIN(1),
    LOSE(-1),
    WIN_BY_BLACKJACK(1.5);

    private final double profitRatio;

    GameResult(final double profitRatio) {
        this.profitRatio = profitRatio;
    }

    public double getProfitRatio() {
        return profitRatio;
    }
}

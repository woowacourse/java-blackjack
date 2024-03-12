package domain.constant;

public enum GameResult {
    WIN(1), LOSE(-1), WIN_BY_BLACKJACK(1.5);

    private final double profit;

    GameResult(final double profit) {
        this.profit = profit;
    }

    public double getProfit() {
        return profit;
    }
}

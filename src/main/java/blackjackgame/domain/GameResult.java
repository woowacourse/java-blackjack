package blackjackgame.domain;

public enum GameResult {
    BLACKJACK_WIN(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private final double profit;

    GameResult(final double profit) {
        this.profit = profit;
    }

    public double profit() {
        return profit;
    }
}

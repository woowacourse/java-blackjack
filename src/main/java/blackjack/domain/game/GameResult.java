package blackjack.domain.game;

public enum GameResult {
    BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0),
    BOTH_BUSTED(-1);

    private final double profitRate;

    GameResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public double getProfitRate() {
        return profitRate;
    }
}

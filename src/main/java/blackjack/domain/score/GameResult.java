package blackjack.domain.score;

public enum GameResult {
    WIN_BY_BLACK_JACK(1.5),
    WIN(1.0),
    LOSE(-1),
    DRAW(0);

    private final double moneyReturnRate;

    GameResult(double moneyReturnRate) {
        this.moneyReturnRate = moneyReturnRate;
    }

    public double getMoneyRate() {
        return moneyReturnRate;
    }
}

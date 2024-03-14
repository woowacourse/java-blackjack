package blackjack.domain.rule;

public enum GameResult {

    BLACKJACK_WIN(1.5),
    PLAYER_WIN(1.0),
    PLAYER_LOSE(-1.0),
    PUSH(0.0);

    private final double profitLeverage;

    GameResult(double profitLeverage) {
        this.profitLeverage = profitLeverage;
    }

    public double getProfitLeverage() {
        return profitLeverage;
    }
}

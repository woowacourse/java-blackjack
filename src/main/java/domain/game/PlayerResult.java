package domain.game;

public enum PlayerResult {

    WIN(1.0),
    TIE(0.0),
    LOSE(-1.0),
    BLACKJACK(1.5),
    ;

    private final double profitRate;

    PlayerResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public double getProfitRate() {
        return profitRate;
    }
}

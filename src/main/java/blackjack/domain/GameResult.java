package blackjack.domain;

public enum GameResult {

    BLACKJACK_WIN(1.5),
    WIN(1),
    TIE(0),
    LOSE(-1);

    private final double profitMultiplier;

    GameResult(double profitMultiplier) {
        this.profitMultiplier = profitMultiplier;
    }

    public double getProfitMultiplier() {
        return profitMultiplier;
    }
}

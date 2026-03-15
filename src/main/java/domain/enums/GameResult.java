package domain.enums;

public enum GameResult {

    BLACKJACK_WIN(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0),
    ;

    private final double profitRatio;

    GameResult(double profitRatio) {
        this.profitRatio = profitRatio;
    }

    public double getProfitRatio() {
        return profitRatio;
    }
}

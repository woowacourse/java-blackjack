package blackjack.domain.game;

public enum GameOutcome {

    BLACKJACK_WIN(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0)
    ;

    private final double profitRate;

    GameOutcome(final double profitRate) {
        this.profitRate = profitRate;
    }

    public static GameOutcome calculateOutcome(final int score, final int compareScore) {
        if (score > compareScore) {
            return WIN;
        }
        if (score < compareScore) {
            return LOSE;
        }
        return DRAW;
    }

    public double getProfitRate() {
        return profitRate;
    }
}

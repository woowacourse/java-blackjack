package blackjack.domain.game;

public enum GameResult {
    WIN(1.0),
    TIE(0.0),
    LOSE(-1.0),
    BLACKJACK(1.5),
    ;

    private final double ratio;

    GameResult(final double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }
}

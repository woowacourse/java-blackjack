package domain.game;

public enum GameResult {

    BLACKJACK_WIN(2.5),
    WIN(2),
    LOSE(-1),
    DRAW(1);

    private final double prizeRatio;

    GameResult(final double prizeRatio) {
        this.prizeRatio = prizeRatio;
    }

    public double prizeRatio() {
        return prizeRatio;
    }
}

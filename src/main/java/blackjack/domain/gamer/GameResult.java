package blackjack.domain.gamer;

public enum GameResult {

    WIN(1.0),
    LOSE(-1.0),
    DRAW(0),
    BLACKJACK_WIN(1.5);

    private final double ratio;

    GameResult(double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }
}

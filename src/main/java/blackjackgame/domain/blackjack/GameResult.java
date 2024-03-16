package blackjackgame.domain.blackjack;

public enum GameResult {
    WIN(1.0),
    BIG_WIN(1.5),
    LOSE(-1.0);

    private final Double times;

    GameResult(Double times) {
        this.times = times;
    }

    public Double getTimes() {
        return times;
    }
}

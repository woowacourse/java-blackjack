package blackjack.domain;

public enum GameResult {
    LOSE,
    DRAW,
    WIN;

    public GameResult getDealerResult() {
        if (this.equals(GameResult.WIN)) {
            return GameResult.LOSE;
        }
        if (this.equals(GameResult.LOSE)) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }
}

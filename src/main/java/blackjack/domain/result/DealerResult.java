package blackjack.domain.result;

public class DealerResult {
    private final GameResultType gameResultType;
    private final Score score;

    public DealerResult(GameResultType gameResultType, Score score) {
        this.gameResultType = gameResultType;
        this.score = score;
    }

    public GameResultType getGameResultType() {
        return gameResultType;
    }

    public int getScoreValue() {
        return score.getScoreValue();
    }
}

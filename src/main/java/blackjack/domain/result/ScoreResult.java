package blackjack.domain.result;

public class ScoreResult {
    private final String name;
    private final GameResult gameResult;

    public ScoreResult(String name, GameResult gameResult) {
        this.name = name;
        this.gameResult = gameResult;
    }

    public String getName() {
        return name;
    }

    public GameResult getGameResult() {
        return gameResult;
    }
}

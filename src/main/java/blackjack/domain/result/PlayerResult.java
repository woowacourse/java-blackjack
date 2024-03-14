package blackjack.domain.result;

public class PlayerResult {

    private final String playerName;
    private final GameResult gameResult;

    public PlayerResult(String playerName, GameResult gameResult) {
        this.playerName = playerName;
        this.gameResult = gameResult;
    }

    public String getPlayerName() {
        return playerName;
    }

    public GameResult getGameResult() {
        return gameResult;
    }
}

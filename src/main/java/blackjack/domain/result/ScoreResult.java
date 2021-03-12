package blackjack.domain.result;

import blackjack.domain.participant.Player;

public class ScoreResult {
    private final Player player;
    private final GameResult gameResult;

    public ScoreResult(Player player, GameResult gameResult) {
        this.player = player;
        this.gameResult = gameResult;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public double calculateEarnings() {
        return player.calculateEarnings(gameResult);
    }
}

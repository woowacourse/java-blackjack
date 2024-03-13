package blackjack.domain.rule;

import blackjack.domain.player.PlayerName;

public class PlayerResult {

    private final PlayerName playerName;
    private final GameResult gameResult;

    public PlayerResult(PlayerName playerName, GameResult gameResult) {
        this.playerName = playerName;
        this.gameResult = gameResult;
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public GameResult getGameResult() {
        return gameResult;
    }
}

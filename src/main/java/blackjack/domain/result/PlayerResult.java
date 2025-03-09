package blackjack.domain.result;

import blackjack.domain.Player;

public class PlayerResult {
    private final Player player;
    private final GameResultType gameResultType;
    private final int value;

    public PlayerResult(Player player, GameResultType gameResultType, int value) {
        this.player = player;
        this.gameResultType = gameResultType;
        this.value = value;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public GameResultType getGameResultType() {
        return gameResultType;
    }

    public int getValue() {
        return value;
    }

    public Player getPlayer() {
        return player;
    }
}

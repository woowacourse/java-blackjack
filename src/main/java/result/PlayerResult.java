package result;

import participant.value.Money;
import participant.Player;

public class PlayerResult {
    private final Player player;
    private final GameStatus gameStatus;

    public PlayerResult(Player player, GameStatus gameStatus) {
        this.player = player;
        this.gameStatus = gameStatus;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public Money calculateProfit() {
        return player.calculateProfit(gameStatus.getPayoutRate());
    }
}

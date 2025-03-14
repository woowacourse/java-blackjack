package result;

import participant.Money;
import participant.Player;

public class PlayerResult {
    private final Player player;
    private final GameStatus gameStatus;

    public PlayerResult(Player player, GameStatus gameStatus) {
        this.player = player;
        this.gameStatus = gameStatus;
    }

    public Money calculateProfit() {
        return player.calculateProfit(gameStatus.getPayoutRate());
    }
}

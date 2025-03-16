package blackjack.domain.result;

import blackjack.domain.game.Player;

public class PlayerProfit {
    private final Player player;
    private final int profit;

    public PlayerProfit(Player player, int profit) {
        this.player = player;
        this.profit = profit;
    }

    public boolean isResultOf(Player player) {
        return this.player.equals(player);
    }

    public int getProfit() {
        return profit;
    }
}

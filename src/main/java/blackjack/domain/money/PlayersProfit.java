package blackjack.domain.money;

import blackjack.domain.gamer.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayersProfit {
    private final Map<Player, Long> profits;

    public PlayersProfit() {
        this.profits = new HashMap<>();
    }

    public void addPlayerChip(Player player, Long profit) {
        profits.put(player, profit);
    }

    public Long allProfit() {
        return profits.values().stream()
                .mapToLong(Long::longValue)
                .sum();
    }

    public Long findProfitBy(Player player) {
        return profits.get(player);
    }
}

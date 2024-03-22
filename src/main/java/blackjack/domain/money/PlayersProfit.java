package blackjack.domain.money;

import blackjack.domain.gamer.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayersProfit {
    private final Map<Player, Double> profits;

    public PlayersProfit() {
        this.profits = new HashMap<>();
    }

    public void addPlayerChip(Player player, Double profit) {
        profits.put(player, profit);
    }

    public Double allProfit() {
        return profits.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public Double findProfitBy(Player player) {
        return profits.get(player);
    }
}

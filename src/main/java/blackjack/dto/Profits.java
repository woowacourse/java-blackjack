package blackjack.dto;

import blackjack.domain.GameResult;
import blackjack.domain.participant.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Profits {

    private final Map<Player, Integer> profits;

    public Profits() {
        this.profits = new HashMap<>();
    }

    public void addProfit(final Player player, final GameResult gameResult) {
        profits.put(player, player.calculateProfit(gameResult));
    }

    public int sumProfits() {
        return profits.values().stream()
                .mapToInt(i -> i)
                .sum();
    }

    public int findByPlayer(final Player player) {
        if (!getProfits().containsKey(player)) {
           throw new IllegalArgumentException();
        }

        return profits.get(player);
    }

    public Map<Player, Integer> getProfits() {
        return Collections.unmodifiableMap(profits);
    }
}

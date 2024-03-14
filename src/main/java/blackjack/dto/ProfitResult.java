package blackjack.dto;

import blackjack.domain.GameResult;
import blackjack.domain.participant.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ProfitResult {

    private final Map<Player, Integer> profitResult;

    public ProfitResult() {
        this.profitResult = new HashMap<>();
    }

    public void addProfitResult(final Player player, final GameResult gameResult) {
        profitResult.put(player, player.calculateProfit(gameResult));
    }

    public int sumAllProfit() {
        return profitResult.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int findByPlayer(final Player player) {
        if (!getProfitResult().containsKey(player)) {
           throw new IllegalArgumentException();
        }

        return profitResult.get(player);
    }

    public Map<Player, Integer> getProfitResult() {
        return Collections.unmodifiableMap(profitResult);
    }
}

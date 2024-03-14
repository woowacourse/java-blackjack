package blackjack.dto;

import blackjack.domain.GameResult;
import blackjack.domain.participant.Player;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ProfitResult {

    private final Map<Player, BigDecimal> profitResult;

    public ProfitResult() {
        this.profitResult = new HashMap<>();
    }

    public void addProfitResult(final Player player, final GameResult gameResult) {
        profitResult.put(player, player.calculateProfit(gameResult));
    }

    public BigDecimal sumAllProfit() {
        return profitResult.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal findByPlayer(final Player player) {
        if (!getProfitResult().containsKey(player)) {
           throw new IllegalArgumentException();
        }

        return profitResult.get(player);
    }

    public Map<Player, BigDecimal> getProfitResult() {
        return Collections.unmodifiableMap(profitResult);
    }
}

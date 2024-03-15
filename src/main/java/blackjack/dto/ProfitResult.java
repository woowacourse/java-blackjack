package blackjack.dto;

import blackjack.domain.participant.Player;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ProfitResult {

    private static final String NONEXISTENT_PLAYER_EXCEPTION = "존재하지 않는 사용자입니다.";

    private final Map<Player, BigDecimal> profitResult;

    public ProfitResult() {
        this.profitResult = new HashMap<>();
    }

    public void addProfitResult(final Player player, final BigDecimal profit) {
        profitResult.put(player, profit);
    }

    public BigDecimal sumAllProfit() {
        return profitResult.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal findByPlayer(final Player player) {
        if (!getProfitResult().containsKey(player)) {
           throw new IllegalArgumentException(NONEXISTENT_PLAYER_EXCEPTION);
        }

        return profitResult.get(player);
    }

    public Map<Player, BigDecimal> getProfitResult() {
        return Collections.unmodifiableMap(profitResult);
    }
}

package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitResult {
    private final Map<Participant, BigDecimal> profitResult;

    public ProfitResult() {
        this.profitResult = new LinkedHashMap<>();
    }

    public void calculateProfit(Map<Player, MatchResult> result, Dealer dealer) {
        BigDecimal dealerProfit = BigDecimal.ZERO;
        profitResult.put(dealer, dealerProfit);
        for (Player player : result.keySet()) {
            BigDecimal profit = player.profit();
            profit = whenDrawStatus(result, player, profit);
            profit = whenLoseStatus(result, player, profit);
            profitResult.put(player, profit);
            dealerProfit = dealerProfit.add(profit.multiply(new BigDecimal("-1")));
        }
        profitResult.put(dealer, dealerProfit);
    }

    private BigDecimal whenDrawStatus(Map<Player, MatchResult> result, Player player, BigDecimal profit) {
        if (result.get(player) == MatchResult.DRAW) {
            profit = new BigDecimal("0");
        }
        return profit;
    }

    private BigDecimal whenLoseStatus(Map<Player, MatchResult> result, Player player, BigDecimal profit) {
        if (result.get(player) == MatchResult.LOSE) {
            profit = profit.multiply(new BigDecimal("-1"));
        }
        return profit;
    }

    public Map<Participant, BigDecimal> getProfitResult() {
        return Collections.unmodifiableMap(profitResult);
    }
}

package blackjack.domain.profit;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitStatistics {
    private static final int NEGATIVE_NUMBER_INDEX = -1;

    private final Map<String, Integer> profitStatistics;

    public ProfitStatistics(Map<String, Integer> profitStatistics) {
        this.profitStatistics = new LinkedHashMap<>(profitStatistics);
    }

    public int calculateDealerProfit() {
        return profitStatistics.values()
                .stream()
                .mapToInt(profit -> profit)
                .sum() * NEGATIVE_NUMBER_INDEX;
    }

    public Map<String, Integer> getProfitStatistics() {
        return Collections.unmodifiableMap(profitStatistics);
    }
}

package blackjack.domain.profit;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitStatistics {
    private static final int NEGATIVE_NUMBER_INDEX = -1;

    private final Map<String, Integer> profitStatistics;

    public ProfitStatistics(Map<String, Integer> profitStatistics) {
        this.profitStatistics = new LinkedHashMap<>(profitStatistics);
    }

    public int calculateDealerProfitMoney() {
        return calculatePlayerProfitMoneySum() * NEGATIVE_NUMBER_INDEX;
    }

    private int calculatePlayerProfitMoneySum() {
        return profitStatistics.values()
                .stream()
                .mapToInt(playerProfitMoney -> playerProfitMoney)
                .sum();
    }

    public Map<String, Integer> getProfitStatistics() {
        return Collections.unmodifiableMap(profitStatistics);
    }
}

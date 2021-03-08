package blackjack.domain.profit;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitStatistics {
    private static final int NEGATIVE_NUMBER_INDEX = -1;

    private final Map<String, Integer> profitStatistics;

    private ProfitStatistics(Map<String, Integer> profitStatistics) {
        this.profitStatistics = profitStatistics;
    }

    public static ProfitStatistics of(Dealer dealer, Players players) {
        Map<String, Integer> profitResultStatistics = new LinkedHashMap<>();
        players.getPlayers()
                .forEach(player -> profitResultStatistics.put(player.getName(), player.calculateProfitMoney(dealer)));
        return new ProfitStatistics(profitResultStatistics);
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

package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResultStatistics {
    private static final int NEGATIVE_NUMBER_INDEX = -1;

    private final Map<String, Integer> profitResultStatistics;

    private ResultStatistics(Map<String, Integer> profitResultStatistics) {
        this.profitResultStatistics = profitResultStatistics;
    }

    public static ResultStatistics of(Dealer dealer, Players players) {
        Map<String, Integer> profitResultStatistics = new LinkedHashMap<>();
        players.toList()
                .forEach(player -> profitResultStatistics.put(player.getName(), player.calculateProfitMoney(dealer)));
        return new ResultStatistics(profitResultStatistics);
    }

    public int calculateDealerProfit() {
        return profitResultStatistics.values()
                .stream()
                .mapToInt(profit -> profit)
                .sum() * NEGATIVE_NUMBER_INDEX;
    }

    public Map<String, Integer> getProfitResultStatistics() {
        return Collections.unmodifiableMap(profitResultStatistics);
    }
}

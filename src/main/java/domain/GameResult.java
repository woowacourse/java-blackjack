package domain;

import domain.player.Dealer;
import domain.player.Gamblers;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private final Map<String, Integer> gamblerProfits;
    private final int dealerProfit;

    public GameResult(Dealer dealer, Gamblers gamblers) {
        this.gamblerProfits = calculateGamblerProfits(dealer, gamblers);
        this.dealerProfit = calculateDealerProfit(gamblerProfits);
    }

    private Map<String, Integer> calculateGamblerProfits(Dealer dealer, Gamblers gamblers) {
        Map<String, Integer> profits = new LinkedHashMap<>();

        gamblers.forEach(gambler -> {
            MatchResult matchResult = MatchResult.of(gambler, dealer);
            int reward = gambler.calculateReward(matchResult);
            profits.put(gambler.getName(), reward);
        });

        return profits;
    }

    private int calculateDealerProfit(Map<String, Integer> gamblerProfits) {
        int totalPlayerProfit = gamblerProfits.values().stream()
                .mapToInt(Integer::intValue)
                .sum();

        return -(totalPlayerProfit);
    }

    public Map<String, Integer> gamblerProfits() {
        return Map.copyOf(gamblerProfits);
    }

    public int dealerProfit() {
        return dealerProfit;
    }
}
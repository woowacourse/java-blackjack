package domain;

import domain.player.Dealer;
import domain.player.Gamblers;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackResult {

    private static final int DEALER_PROFIT_INVERSION = -1;

    private final Map<String, Integer> gamblerProfits;
    private final long dealerProfit;

    private BlackjackResult(Map<String, Integer> gamblerProfits, long dealerProfit) {
        this.gamblerProfits = gamblerProfits;
        this.dealerProfit = dealerProfit;
    }

    public static BlackjackResult from(Dealer dealer, Gamblers gamblers) {
        Map<String, Integer> profits = calculateGamblerProfits(dealer, gamblers);
        long dealerProfit = calculateDealerProfit(profits);
        return new BlackjackResult(profits, dealerProfit);
    }

    private static Map<String, Integer> calculateGamblerProfits(Dealer dealer, Gamblers gamblers) {
        Map<String, Integer> profits = new LinkedHashMap<>();

        gamblers.forEach(gambler -> {
            MatchResult matchResult = MatchResult.of(gambler, dealer);
            int reward = gambler.calculateReward(matchResult);
            profits.put(gambler.getName(), reward);
        });

        return profits;
    }

    private static long calculateDealerProfit(Map<String, Integer> gamblerProfits) {
        long totalGamblerProfit = gamblerProfits.values().stream()
                .mapToLong(Long::valueOf)
                .sum();

        return totalGamblerProfit * DEALER_PROFIT_INVERSION;
    }

    public Map<String, Integer> gamblerProfits() {
        return Map.copyOf(gamblerProfits);
    }

    public long dealerProfit() {
        return dealerProfit;
    }
}
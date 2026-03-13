package domain;

import domain.player.Dealer;
import domain.player.Gamblers;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackResult {

    private static final int DEALER_PROFIT_INVERSION = -1;

    private final Map<String, Long> gamblerProfits;
    private final long dealerProfit;

    private BlackjackResult(Map<String, Long> gamblerProfits, long dealerProfit) {
        this.gamblerProfits = gamblerProfits;
        this.dealerProfit = dealerProfit;
    }

    public static BlackjackResult from(Dealer dealer, Gamblers gamblers) {
        Map<String, Long> profits = calculateGamblerProfits(dealer, gamblers);
        long dealerProfit = calculateDealerProfit(profits);
        return new BlackjackResult(profits, dealerProfit);
    }

    private static Map<String, Long> calculateGamblerProfits(Dealer dealer, Gamblers gamblers) {
        Map<String, Long> profits = new LinkedHashMap<>();

        gamblers.forEach(gambler -> {
            MatchResult matchResult = MatchResult.of(gambler, dealer);
            long reward = gambler.calculateReward(matchResult.getRate());
            profits.put(gambler.getName(), reward);
        });

        return profits;
    }

    private static long calculateDealerProfit(Map<String, Long> gamblerProfits) {
        long totalGamblerProfit = gamblerProfits.values().stream()
                .mapToLong(Long::valueOf)
                .sum();

        return totalGamblerProfit * DEALER_PROFIT_INVERSION;
    }

    public Map<String, Long> gamblerProfits() {
        return Map.copyOf(gamblerProfits);
    }

    public long dealerProfit() {
        return dealerProfit;
    }
}
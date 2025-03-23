package bet;

import participant.Dealer;
import participant.Player;
import strategy.winning.WinningStrategy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BetCenter {

    private final Map<Player, BetAmount> playerBetAmounts;
    private final List<WinningStrategy> strategies;

    public BetCenter(Map<Player, BetAmount> playerBetAmounts, List<WinningStrategy> strategies) {
        this.playerBetAmounts = playerBetAmounts;
        this.strategies = strategies;
    }

    public int calculateDealerProfit(Dealer dealer) {
        return deriveBettingResults(dealer).values().stream()
                .mapToInt(value -> -value)
                .sum();
    }

    public Map<Player, Integer> deriveBettingResults(Dealer dealer) {
        Map<Player, Integer> bettingResults = new LinkedHashMap<>();
        for (Player player : playerBetAmounts.keySet()) {
            bettingResults.put(player, (int) calculateProfit(player, dealer));
        }
        return bettingResults;
    }

    private double calculateProfit(Player player, Dealer dealer) {
        BetAmount betAmount = playerBetAmounts.get(player);

        return strategies.stream()
                .filter(strategy -> strategy.matches(player, dealer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 플레이어와 딜러의 점수에 부합하는 승패 규칙이 없습니다."))
                .getProfitCalculator()
                .calculate(betAmount);
    }
}

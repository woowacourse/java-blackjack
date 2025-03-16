package bet;

import participant.Dealer;
import participant.Player;
import strategy.winning.BlackJackWinStrategy;
import strategy.winning.DealerBlackJackStrategy;
import strategy.winning.DrawStrategy;
import strategy.winning.LoseStrategy;
import strategy.winning.NormalWinStrategy;
import strategy.winning.WinningStrategy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BetCenter {

    private static final List<WinningStrategy> strategies = List.of(
            new BlackJackWinStrategy(),
            new DealerBlackJackStrategy(),
            new NormalWinStrategy(),
            new DrawStrategy(),
            new LoseStrategy()
    );

    private final Map<Player, BetAmount> playerBetAmounts;

    public BetCenter(Map<Player, BetAmount> playerBetAmounts) {
        this.playerBetAmounts = playerBetAmounts;
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

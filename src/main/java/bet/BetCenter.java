package bet;

import constant.WinningResult;
import participant.Dealer;
import participant.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class BetCenter {

    private static final int HIGHEST_SCORE = 21;

    private final Map<Player, BetAmount> playerBetAmounts;

    public BetCenter(Map<Player, BetAmount> playerBetAmounts) {
        this.playerBetAmounts = playerBetAmounts;
    }

    public int calculateDealerProfit(Dealer dealer) {
        return deriveBettingResults(dealer).values().stream()
                .mapToInt(value -> -value.getValue())
                .sum();
    }

    public Map<Player, BetAmount> deriveBettingResults(Dealer dealer) {
        Map<Player, BetAmount> bettingResults = new LinkedHashMap<>();
        for (Player player : playerBetAmounts.keySet()) {
            bettingResults.put(player, new BetAmount(calculateProfit(player, dealer)));
        }
        return bettingResults;
    }

    private double calculateProfit(Player player, Dealer dealer) {
        BetAmount betAmount = playerBetAmounts.get(player);
        if (isOnlyDealerBlackJack(player, dealer)) {
            return WinningResult.LOSE.calculateProfit(betAmount);
        }
        if (player.sumCardNumbers() == dealer.sumCardNumbers()) {
            return WinningResult.DRAW.calculateProfit(betAmount);
        }
        if (player.isBlackJack()) {
            return WinningResult.BLACKJACK.calculateProfit(betAmount);
        }
        if (player.compareTo(dealer.sumCardNumbers()) == WinningResult.WIN) {
            return WinningResult.WIN.calculateProfit(betAmount);
        }

        return WinningResult.LOSE.calculateProfit(betAmount);
    }

    private boolean isOnlyDealerBlackJack(Player player, Dealer dealer) {
        return (dealer.isBlackJack() && !player.isBlackJack());
    }
}

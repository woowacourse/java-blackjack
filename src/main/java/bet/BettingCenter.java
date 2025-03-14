package bet;

import constant.WinningResult;
import participant.Dealer;
import participant.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingCenter {

    private static final int HIGHEST_SCORE = 21;

    private final Map<Player, Integer> playerBetAmounts;

    public BettingCenter(Map<Player, Integer> playerBetAmounts) {
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
        if (isBothBlackJack(player, dealer) || isBothHighestScore(player, dealer)) {
            return playerBetAmounts.get(player) * WinningResult.DRAW.getProfitRate();
        }

        if (player.isBlackJack()) {
            return (double) playerBetAmounts.get(player) * WinningResult.BLACKJACK.getProfitRate();
        }

        if (player.compareTo(dealer.sumCardNumbers()) == WinningResult.WIN) {
            return playerBetAmounts.get(player) * WinningResult.WIN.getProfitRate();
        }

        return playerBetAmounts.get(player) * WinningResult.LOSE.getProfitRate();
    }

    private boolean isBothHighestScore(Player player, Dealer dealer) {
        return dealer.sumCardNumbers() == HIGHEST_SCORE && player.sumCardNumbers() == HIGHEST_SCORE;
    }

    private boolean isBothBlackJack(Player player, Dealer dealer) {
        return dealer.isBlackJack() && player.isBlackJack();
    }
}

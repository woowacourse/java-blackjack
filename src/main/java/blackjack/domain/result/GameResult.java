package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private final Map<Player, Integer> gameResult;

    private GameResult(Map<Player, Integer> gameResult) {
        this.gameResult = gameResult;
    }

    public static GameResult of(Dealer dealer, List<Gamer> gamers) {
        Map<Player, Integer> result = new LinkedHashMap<>();
        int dealerProfit = 0;
        for (Gamer gamer : gamers) {
            int gamerProfit = calculateGamerProfit(gamer, dealer);
            result.put(gamer, gamerProfit);
            dealerProfit -= gamerProfit;
        }
        result.put(dealer, dealerProfit);
        return new GameResult(result);
    }

    private static int calculateGamerProfit(Gamer gamer, Dealer dealer) {
        int profit = gamer.profit();
        if (gamer.isBlackJack() && dealer.isBlackJack()) {
            return 0;
        }
        if (gamer.isBlackJack() && !dealer.isBlackJack()) {
            return profit;
        }
        return calculateGamerProfitIfNotBlackjack(gamer, dealer, profit);
    }

    private static int calculateGamerProfitIfNotBlackjack(Gamer gamer, Dealer dealer, int profit) {
        if (gamer.isBust()) {
            return profit - gamer.getBettingMoney();
        }
        if (!gamer.isBust() && dealer.isBlackJack()) {
            return profit * (-1);
        }
        if (!gamer.isBust() && dealer.isBust()) {
            return profit;
        }
        return calculateGamerProfitIfAllStay(gamer, dealer, profit);
    }

    private static int calculateGamerProfitIfAllStay(Gamer gamer, Dealer dealer, int profit) {
        if (gamer.calculateScore() > dealer.calculateScore()) {
            return profit;
        }
        if (gamer.calculateScore() < dealer.calculateScore()) {
            return profit * (-1);
        }
        return 0;
    }

    public int findProfitByPlayer(Player player) {
        return gameResult.get(player);
    }
}
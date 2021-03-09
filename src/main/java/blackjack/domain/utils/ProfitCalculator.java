package blackjack.domain.utils;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitCalculator {

    public static final String DEALER = "딜러";

    private ProfitCalculator() {
    }

    public static Map<String, Double> calculateProfitOf(Dealer dealer, Players players) {
        final Map<String, Double> result = new LinkedHashMap<>();
        result.put(DEALER, 0.0);
        for (Player player : players) {
            compareResult(result, dealer, player);
        }
        return result;
    }

    private static void compareResult(Map<String, Double> result, Dealer dealer, Player player) {
        result.put(player.getName(), 0.0);
        if (bothBlackjack(dealer, player)) {
            return;
        }
        if (bothStay(dealer, player)) {
            updateResult(result, player.getName(),
                    player.getProfit() * Integer.compare(player.getScore(), dealer.getScore()));
            return;
        }
        updateResult(result, player.getName(), player.getProfit());
    }

    private static void updateResult(Map<String, Double> result, String name, double profit) {
        result.replace(name, profit);
        result.replace(DEALER, result.get(DEALER) + convert(profit));
    }

    private static boolean bothStay(Dealer dealer, Player player) {
        return !(dealer.isBust() && player.isBust());
    }

    private static boolean bothBlackjack(Dealer dealer, Player player) {
        return dealer.isBlackjack() && player.isBlackjack();
    }

    private static double convert(double value) {
        return -1.0 * value;
    }
}

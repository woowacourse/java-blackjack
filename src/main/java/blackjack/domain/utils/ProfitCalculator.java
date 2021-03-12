package blackjack.domain.utils;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitCalculator {

    private static final String DEALER = "딜러";
    private static final double ZERO = 0.0;
    private static final double REVERSE = -1.0;

    private ProfitCalculator() {
    }

    public static Map<String, Double> calculateProfitOf(final Dealer dealer, final Players players) {
        final Map<String, Double> result = new LinkedHashMap<>();
        result.put(DEALER, ZERO);
        for (Player player : players) {
            updateResult(result, player.getName(), compareResult(dealer, player));
        }
        return result;
    }

    private static double compareResult(final Dealer dealer, final Player player) {
        if (bothBlackjack(dealer, player)) {
            return ZERO;
        }
        if (bothStay(dealer, player)) {
            return player.getProfit() * Integer.compare(player.getScore(), dealer.getScore());
        }
        return player.getProfit();
    }

    private static void updateResult(final Map<String, Double> result, final String name, final double profit) {
        result.put(name, profit);
        result.replace(DEALER, result.get(DEALER) + reverse(profit));
    }

    private static boolean bothStay(final Dealer dealer, final Player player) {
        return dealer.isStay() && player.isStay();
    }

    private static boolean bothBlackjack(final Dealer dealer, final Player player) {
        return dealer.isBlackjack() && player.isBlackjack();
    }

    private static double reverse(final double value) {
        return REVERSE * value;
    }
}

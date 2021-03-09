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
            compareResult(result, dealer ,player);
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
                    player.getProfit() * Integer.compare(player.getScore2(), dealer.getScore2()));
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

//    private static final int MAX_WINNING_POINT = 21;
//    public static ResultType decideWinner(Player player, Dealer dealer) {
//        if (player.getScore() > MAX_WINNING_POINT) {
//            return ResultType.LOSE;
//        }
//        if (dealer.getScore() > MAX_WINNING_POINT) {
//            return ResultType.WIN;
//        }
//        return findWinner(player, dealer);
//    }
//
//    private static ResultType findWinner(Player player, Dealer dealer) {
//        if (bothUnderWinningPoint(player, dealer)) {
//            return compare(player, dealer);
//        }
//        if (bothWinningPoint(player, dealer)) {
//            return checkBlackjack(player, dealer);
//        }
//        return confirmPlayerWinningPoint(player);
//    }
//
//    private static boolean bothWinningPoint(Player player, Dealer dealer) {
//        return player.getScore() == MAX_WINNING_POINT && dealer.getScore() == MAX_WINNING_POINT;
//    }
//
//    private static boolean bothUnderWinningPoint(Player player, Dealer dealer) {
//        return player.getScore() < MAX_WINNING_POINT &&
//                dealer.getScore() < MAX_WINNING_POINT;
//    }
//
//    private static ResultType compare(Player player, Dealer dealer) {
//        int compare = Integer.compare(player.getScore(), dealer.getScore());
//        if (compare < 0) {
//            return ResultType.LOSE;
//        }
//        if (compare > 0) {
//            return ResultType.WIN;
//        }
//        return ResultType.DRAW;
//    }
//
//    private static ResultType checkBlackjack(Player player, Dealer dealer) {
//        if (player.isBlackjack() && dealer.isBlackjack()) {
//            return ResultType.DRAW;
//        }
//        if (player.isBlackjack()) {
//            return ResultType.WIN;
//        }
//        if (dealer.isBlackjack()) {
//            return ResultType.LOSE;
//        }
//        return ResultType.DRAW;
//    }
//
//    private static ResultType confirmPlayerWinningPoint(Player player) {
//        if (player.getScore() == MAX_WINNING_POINT) {
//            return ResultType.WIN;
//        }
//        return ResultType.LOSE;
//    }
}

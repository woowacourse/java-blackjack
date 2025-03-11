package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public enum ResultStatus {
    WIN(betAmount -> betAmount),
    LOSE(betAmount -> -betAmount),
    PUSH(betAmount -> 0),
    BLACK_JACK(betAmount -> (int) (betAmount * 1.5));

    private final Function<Integer, Integer> calculateProfit;

    ResultStatus(Function<Integer, Integer> calculateProfit) {
        this.calculateProfit = calculateProfit;
    }


    public static Map<Player, ResultStatus> judgeGameResult(Players players, Dealer dealer) {
        Map<Player, ResultStatus> result = new HashMap<>();

        Map<Player, Integer> totalNumberSumByPlayer = players.getTotalNumberSumByPlayer();
        for (Player player : totalNumberSumByPlayer.keySet()) {
            judgeGameResultByPlayer(dealer, player, result);
        }
        return result;
    }

    private static void judgeGameResultByPlayer(Dealer dealer, Player player, Map<Player, ResultStatus> result) {
        if (isPlayerBust(player, result)) return;
        if (isPlayerBlackJack(player, result)) return;
        if (isDealerBust(dealer, player, result)) return;
        compareDifference(dealer, player, result);
    }

    private static boolean isPlayerBust(Player player, Map<Player, ResultStatus> result) {
        if (player.isBurst()) {
            result.put(player, ResultStatus.LOSE);
            return true;
        }
        return false;
    }

    private static boolean isPlayerBlackJack(Player player, Map<Player, ResultStatus> result) {
        if (player.isBlackJack()) {
            result.put(player, ResultStatus.BLACK_JACK);
            return true;
        }
        return false;
    }

    private static boolean isDealerBust(Dealer dealer, Player player, Map<Player, ResultStatus> result) {
        if (dealer.isBurst()) {
            result.put(player, ResultStatus.WIN);
            return true;
        }
        return false;
    }

    private static void compareDifference(Dealer dealer, Player player, Map<Player, ResultStatus> result) {
        int dealerAbs = dealer.calculateDifferenceFromTwentyOne();
        int playerAbs = player.calculateDifferenceFromTwentyOne();
        if (playerAbs > dealerAbs) {
            result.put(player, ResultStatus.LOSE);
            return;
        }
        if (playerAbs == dealerAbs) {
            result.put(player, ResultStatus.PUSH);
            return;
        }
        result.put(player, ResultStatus.WIN);
    }

    public int calculateIncome(int betAmount) {
        return calculateProfit.apply(betAmount);
    }
}

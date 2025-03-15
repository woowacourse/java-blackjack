package domain;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public enum GameResult {

    WIN(bettingAmount -> bettingAmount),
    LOSE(bettingAmount -> -bettingAmount),
    PUSH(bettingAmount -> 0),
    BLACKJACK(bettingAmount -> (int) (bettingAmount * 1.5)),
    ;

    private final Function<Integer, Integer> profitsCalculator;

    GameResult(Function<Integer, Integer> profitsCalculator) {
        this.profitsCalculator = profitsCalculator;
    }

    public int calculateProfit(int bettingAmount) {
        return profitsCalculator.apply(bettingAmount);
    }

    public static Map<Player, Integer> calculateProfits(Dealer dealer, Players players) {
        Map<Player, Integer> profitsByPlayer = new LinkedHashMap<>();
        Map<Player, GameResult> resultStatuses = judgeGameResult(dealer, players);
        for (Player player : resultStatuses.keySet()) {
            GameResult gameResult = resultStatuses.get(player);
            profitsByPlayer.put(player, gameResult.calculateProfit(player.getBettingAmount()));
        }
        return profitsByPlayer;
    }

    private static Map<Player, GameResult> judgeGameResult(Dealer dealer, Players players) {
        Map<Player, GameResult> result = new LinkedHashMap<>();
        Map<Player, Integer> totalRankSumByPlayer = players.getTotalRankSumByPlayer();
        for (Player player : totalRankSumByPlayer.keySet()) {
            judgeGameResultByPlayer(dealer, player, result);
        }
        return result;
    }

    private static void judgeGameResultByPlayer(Dealer dealer, Player player, Map<Player, GameResult> result) {
        if (player.isBlackjack()) {
            result.put(player, GameResult.BLACKJACK);
            return;
        }
        if (player.isBust()) {
            result.put(player, GameResult.LOSE);
            return;
        }
        if (dealer.isBust()) {
            result.put(player, GameResult.WIN);
            return;
        }
        compareDifference(dealer, player, result);
    }

    private static void compareDifference(Dealer dealer, Player player, Map<Player, GameResult> result) {
        int dealerTotal = dealer.getTotalRankSum();
        int playerTotal = player.getTotalRankSum();
        if (dealerTotal > playerTotal) {
            result.put(player, GameResult.LOSE);
            return;
        }
        if (dealerTotal == playerTotal) {
            result.put(player, GameResult.PUSH);
            return;
        }
        result.put(player, GameResult.WIN);
    }

    public static int calculateDealerProfits(Dealer dealer, Players players) {
        return -calculateProfits(dealer, players).values()
                .stream()
                .mapToInt(profit -> profit)
                .sum();
    }
}

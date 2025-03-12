package domain;

import domain.participant.Participant;
import domain.participant.Participants;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public enum GameResult {
    WIN(bettingAmount -> bettingAmount),
    LOSE(bettingAmount -> -bettingAmount),
    PUSH(bettingAmount -> 0),
    BLACKJACK(bettingAmount -> (int) (bettingAmount * 1.5));

    private final Function<Integer, Integer> profitsCalculator;

    GameResult(Function<Integer, Integer> profitsCalculator) {
        this.profitsCalculator = profitsCalculator;
    }

    public int calculateProfit(int bettingAmount) {
        return profitsCalculator.apply(bettingAmount);
    }

    public static Map<Participant, Integer> calculateProfits(Participants participants) {
        Map<Participant, Integer> profitsByPlayer = new LinkedHashMap<>();
        Map<Participant, GameResult> resultStatuses = judgeGameResult(participants);
        for (Participant player : resultStatuses.keySet()) {
            GameResult gameResult = resultStatuses.get(player);
            profitsByPlayer.put(player, gameResult.calculateProfit(player.getBettingAmount()));
        }
        return profitsByPlayer;
    }

    private static Map<Participant, GameResult> judgeGameResult(Participants participants) {
        Map<Participant, GameResult> result = new LinkedHashMap<>();
        Map<Participant, Integer> totalRankSumByPlayer = participants.getTotalRankSumByPlayer();
        for (Participant player : totalRankSumByPlayer.keySet()) {
            judgeGameResultByPlayer(participants.findDealer(), player, result);
        }
        return result;
    }

    private static void judgeGameResultByPlayer(Participant dealer, Participant player, Map<Participant, GameResult> result) {
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

    private static void compareDifference(Participant dealer, Participant player, Map<Participant, GameResult> result) {
        int dealerAbs = dealer.calculateDifferenceFromBlackjackScore();
        int playerAbs = player.calculateDifferenceFromBlackjackScore();
        if (playerAbs > dealerAbs) {
            result.put(player, GameResult.LOSE);
            return;
        }
        if (playerAbs == dealerAbs) {
            result.put(player, GameResult.PUSH);
            return;
        }
        result.put(player, GameResult.WIN);
    }

    public static int calculateDealerProfits(Participants participants) {
        return -calculateProfits(participants).values()
                .stream()
                .mapToInt(profit -> profit)
                .sum();
    }
}

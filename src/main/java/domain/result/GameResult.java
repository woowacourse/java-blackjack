package domain.result;

import domain.participant.Participant;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private static final int BUST_HAND_VALUE = 0;
    private final Map<Participant, Integer> gameResult;
    private final Participant dealer;

    public GameResult(final Map<Participant, Integer> gameResult, final Participant dealer) {
        this.gameResult = gameResult;
        this.dealer = dealer;
    }

    public Map<Participant, Boolean> getParticipantsBustStatus() {
        Map<Participant, Boolean> scores = new LinkedHashMap<>();
        scores.put(dealer, dealer.isBust());
        for (Participant participant : gameResult.keySet()) {
            scores.put(participant, participant.isBust());
        }

        return scores;
    }

    public Map<Result, Integer> getDealerStatus(Map<Participant, Result> results) {
        Map<Result, Integer> DealerWinningStatus = new EnumMap<>(Result.class);

        for (Result playerResult : results.values()) {
            judgeResult(DealerWinningStatus, playerResult);
        }
        return DealerWinningStatus;
    }

    public Map<Participant, Result> getPlayerStatus() {
        Map<Participant, Result> playerResults = new LinkedHashMap<>();
        for (Participant player : gameResult.keySet()) {
            compareHandValue(playerResults, player);
        }

        return playerResults;
    }

    private void judgeResult(Map<Result, Integer> result, Result playerResult) {
        if (playerResult.equals(Result.WIN)) {
            result.put(Result.LOSE, result.getOrDefault(Result.LOSE, 0) + 1);
        }
        if (playerResult.equals(Result.TIE)) {
            result.put(Result.TIE, result.getOrDefault(Result.TIE, 0) + 1);
        }
        if (playerResult.equals(Result.LOSE)) {
            result.put(Result.WIN, result.getOrDefault(Result.WIN, 0) + 1);
        }
    }

    private void compareHandValue(Map<Participant, Result> playerResults, Participant player) {
        int dealerHandValue = getParticipantHandValue(dealer);
        int playerHandValue = getParticipantHandValue(player);

        if (playerHandValue != dealerHandValue) {
            playerResults.put(player, Result.isHigherPlayerHandValue(playerHandValue, dealerHandValue));
            return;
        }
        compareAtTieValue(dealer, playerResults, player, playerHandValue);
    }

    private int getParticipantHandValue(Participant participant) {
        if (participant.isBust()) {
            return 0;
        }
        return participant.getHandValue();
    }

    private void compareAtTieValue(Participant dealer, Map<Participant, Result> playerResults, Participant player, int playerHandValue) {
        if (playerHandValue == BUST_HAND_VALUE) {
            playerResults.put(player, Result.TIE);
            return;
        }
        playerResults.put(player, compareHandCount(dealer, player));
    }
    private Result compareHandCount(Participant dealer, Participant player) {
        int playerHandCount = player.getCardNames().size();
        int dealerHandCount = dealer.getCardNames().size();
        return Result.isGreaterPlayerHandCount(playerHandCount, dealerHandCount);
    }
}

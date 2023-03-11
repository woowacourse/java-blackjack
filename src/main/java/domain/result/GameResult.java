package domain.result;

import domain.participant.Participant;

import java.math.BigDecimal;
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

    public Map<Participant, Integer> getPlayerStatus() {
        Map<Participant, Integer> playersPrize = new LinkedHashMap<>();

        for (Participant player : gameResult.keySet()) {
            if (player.isBlackjack()) {
                playersPrize.put(player, calculatePrizeWhenPlayerBlackjack(player));
                continue;
            }

            Result result = compareHandValue(player);
            playersPrize.put(player, player.calculatePrize(result.getWidth()));
        }
        return playersPrize;
    }

    private int calculatePrizeWhenPlayerBlackjack(Participant player) {
        if (dealer.isBlackjack()) {
            return player.calculatePrize(new BigDecimal(1));

        }
        return player.calculatePrize(new BigDecimal(1.5));
    }

    private Result compareHandValue(Participant player) {
        int dealerHandValue = getParticipantHandValue(dealer);
        int playerHandValue = getParticipantHandValue(player);

        if (playerHandValue != dealerHandValue) {
            return Result.isHigherPlayerHandValue(playerHandValue, dealerHandValue);
        }
        return compareAtTieValue(dealer, player, playerHandValue);
    }

    private Result compareAtTieValue(Participant dealer,  Participant player, int playerHandValue) {
        if (playerHandValue == BUST_HAND_VALUE) {
            return Result.TIE;
        }
        return compareHandCount(dealer, player);
    }

    private Result compareHandCount(Participant dealer, Participant player) {
        int playerHandCount = player.getCardNames().size();
        int dealerHandCount = dealer.getCardNames().size();
        return Result.isGreaterPlayerHandCount(playerHandCount, dealerHandCount);
    }

    private int getParticipantHandValue(Participant participant) {
        if (participant.isBust()) {
            return 0;
        }
        return participant.getHandValue();
    }

}

package blackjack.model.game;

import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;

import java.util.HashMap;
import java.util.Map;

import static blackjack.model.game.ParticipantResult.LOSE;
import static blackjack.model.game.ParticipantResult.WIN;

public class MoneyDistributor {

    public static final int DRAW_MONEY = 0;
    public static final double BLACKJACK_MONEY_RATE = 1.5;

    public static Map<Participant, Long> calculateWinningMoneys(final Dealer dealer, final Map<Participant, ParticipantResult> participantResults) {
        Map<Participant, Long> winningMoneys = new HashMap<>();
        for (Map.Entry<Participant, ParticipantResult> resultEntry : participantResults.entrySet()) {
            Participant participant = resultEntry.getKey();
            winningMoneys.put(participant, calculateWinningMoney(dealer.isBlackJack(), participant, resultEntry.getValue()));
        }
        return winningMoneys;
    }

    private static long calculateWinningMoney(final boolean isDealerBlackJack, final Participant participant, final ParticipantResult participantResult) {
        if (isDealerBlackJack) {
            return calculateWinningMoneyIfDealerBlackJack(participant);
        }
        return calculateWinningMoneyNotBlackJack(participant, participantResult);
    }

    private static long calculateWinningMoneyIfDealerBlackJack(final Participant participant) {
        if (participant.isBlackJack()) {
            return DRAW_MONEY;
        }
        return -participant.getBettedMoney();
    }

    private static long calculateWinningMoneyNotBlackJack(final Participant participant, final ParticipantResult participantResult) {
        if (participant.isBlackJack()) {
            return (long)(participant.getBettedMoney() * BLACKJACK_MONEY_RATE);
        }
        if (participantResult == WIN) {
            return participant.getBettedMoney();
        }
        if (participantResult == LOSE) {
            return -participant.getBettedMoney();
        }
        return DRAW_MONEY;
    }

    public static long calculateDealerMoney(final Map<Participant, Long> winningMoneys) {
        return -winningMoneys.values().stream()
                .mapToLong(Long::longValue)
                .sum();
    }
}

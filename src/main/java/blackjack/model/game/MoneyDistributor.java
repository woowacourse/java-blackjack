package blackjack.model.game;

import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;

import java.util.HashMap;
import java.util.Map;

import static blackjack.model.game.ParticipantResult.LOSE;
import static blackjack.model.game.ParticipantResult.WIN;

public class MoneyDistributor {

    public static final int DRAW_MONEY = 0;
    public static final double WINNING_MONEY_RATE = 1.5;

    public static Map<Participant, Integer> calculateWinningMoneys(final Dealer dealer, final Map<Participant, ParticipantResult> participantResults) {
        Map<Participant, Integer> winningMoneys = new HashMap<>();
        for (Map.Entry<Participant, ParticipantResult> resultEntry : participantResults.entrySet()) {
            Participant participant = resultEntry.getKey();
            winningMoneys.put(participant, calculateWinningMoney(dealer.isBlackJack(), participant, resultEntry.getValue()));
        }
        return winningMoneys;
    }

    private static int calculateWinningMoney(final boolean isDealerBlackJack, final Participant participant, final ParticipantResult participantResult) {
        if (isDealerBlackJack) {
            return calculateWinningMoneyIfDealerBlackJack(participant);
        }
        return calculateWinningMoneyNotBlackJack(participant.getBettedMoney(), participant.isBlackJack(), participantResult);
    }

    private static int calculateWinningMoneyIfDealerBlackJack(final Participant participant) {
        if (participant.isBlackJack()) {
            return DRAW_MONEY;
        }
        return -participant.getBettedMoney();
    }

    private static int calculateWinningMoneyNotBlackJack(final int bettedMoney, final boolean isBlackJack, final ParticipantResult participantResult) {
        if (isBlackJack) {
            return (int)(bettedMoney * WINNING_MONEY_RATE);
        }
        if (participantResult == WIN) {
            return bettedMoney;
        }
        if (participantResult == LOSE) {
            return -bettedMoney;
        }
        return DRAW_MONEY;
    }

    public static int calculateDealerMoney(final Map<Participant, Integer> winningMoney) {
        return -winningMoney.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}

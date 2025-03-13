package blackjack.model.game;

import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;

import java.util.LinkedHashMap;
import java.util.Map;

import static blackjack.model.game.ParticipantResult.LOSE;
import static blackjack.model.game.ParticipantResult.WIN;

public class MoneyDistributor {

    public static final int DRAW_MONEY = 0;
    public static final double WINNING_MONEY_RATE = 1.5;

    public static Map<Participant, Integer> calculateWinningMoney(final Dealer dealer, final Map<Participant, ParticipantResult> participantResults) {
        Map<Participant, Integer> winningMoneys = new LinkedHashMap<>();
        for (Map.Entry<Participant, ParticipantResult> resultEntry : participantResults.entrySet()) {
            Participant participant = resultEntry.getKey();
            if (participant.isBlackJack()) {
                calculateWinningMoneyIfBlackJack(dealer, participant, winningMoneys);
                continue;
            }
            calculateWinningMoneyNotBlackJack(participant, resultEntry.getValue(), winningMoneys);
        }
        return winningMoneys;
    }

    private static void calculateWinningMoneyIfBlackJack(final Dealer dealer, final Participant participant, final Map<Participant, Integer> winningMoneys) {
        if (dealer.isBlackJack()) {
            winningMoneys.put(participant, DRAW_MONEY);
            return;
        }
        winningMoneys.put(participant, (int)(participant.getBettedMoney() * WINNING_MONEY_RATE));
    }

    private static void calculateWinningMoneyNotBlackJack(final Participant participant, final ParticipantResult participantResult, final Map<Participant, Integer> winningMoneys) {
        if (participantResult == WIN) {
            winningMoneys.put(participant, participant.getBettedMoney());
            return;
        }
        if (participantResult == LOSE) {
            winningMoneys.put(participant, 0 - participant.getBettedMoney());
            return;
        }
        winningMoneys.put(participant, DRAW_MONEY);
    }

    public static int calculateDealerMoney(final Map<Participant, Integer> winningMoney) {
        return 0 - winningMoney.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}

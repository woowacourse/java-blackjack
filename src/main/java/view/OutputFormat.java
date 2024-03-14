package view;

import domain.Bet.BetAmount;
import domain.Bet.BetResult;
import domain.card.Card;
import domain.participant.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputFormat {

    private static final String DELIMITER = ", ";

    public String formatParticipantNames(Participants participants) {
        List<String> participantNames = participants.getNames()
                .stream()
                .map(Name::getValue)
                .toList();
        return String.format("딜러와 %s에게 2장을 나누었습니다.", String.join(DELIMITER, participantNames));
    }

    public String formatHands(Participant participant) {
        Name name = participant.getName();
        List<Card> cards = participant.getCards();
        List<String> cardNames = new ArrayList<>();
        for (Card card : cards) {
            cardNames.add(formatCard(card));
        }
        return String.format("%s카드: %s", name.getValue(), String.join(DELIMITER, cardNames));
    }

    public String formatCard(Card card) {
        return card.getRank().getName() + card.getShape().getName();
    }

    public String formatDealerResult(BetResult betResult) {
        double dealerProfit = betResult.calculateDealerProfit();
        return String.format("딜러: %d", (int)dealerProfit);
    }

    public String formatParticipantResult(Participant participant) {
        return String.format("%s - 결과: %d", formatHands(participant), participant.getScore());
    }

    public String formatBetResult(Map.Entry<Participant, BetAmount> winStatusEntry) {
        return String.format("%s: %d", winStatusEntry.getKey().getName().getValue(), (int)winStatusEntry.getValue().getValue());
    }
}

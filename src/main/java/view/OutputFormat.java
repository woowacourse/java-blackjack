package view;

import domain.blackjack.BlackJackResult;
import domain.blackjack.WinStatus;
import domain.card.Card;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Participants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputFormat {

    private static final String DELIMITER = ", ";

    public String formatParticipantNames(Participants participants) {
        List<Name> names = participants.getNames();
        List<String> participantNames = new ArrayList<>();
        for (Name name : names) {
            participantNames.add(name.getValue());
        }
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
        return RankFormat.from(card.getRank()).getName() + ShapeFormat.from(card.getShape()).getName();
    }

    public String formatDealerResult(BlackJackResult blackJackResult) {
        int totalResult = blackJackResult.getTotalCount();
        int dealerWinCount = blackJackResult.getDealerWinCount();

        return String.format("딜러: %d승 %d패", dealerWinCount, totalResult - dealerWinCount);
    }

    public String formatParticipantResult(Participant participant) {
        return String.format("%s - 결과: %d", formatHands(participant), participant.getScore());
    }

    public String formatBlackJackResult(Map.Entry<Participant, WinStatus> winStatusEntry) {
        if (WinStatus.WIN.equals(winStatusEntry.getValue())) {
            return String.format("%s: 승", winStatusEntry.getKey().getName().getValue());
        }
        return String.format("%s: 패", winStatusEntry.getKey().getName().getValue());
    }
}

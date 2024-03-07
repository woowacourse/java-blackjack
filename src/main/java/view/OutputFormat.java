package view;

import domain.Card;
import domain.Name;
import domain.Participant;
import domain.Participants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputFormat {
    public String formatParticipantNames(Participants participants) {
        List<Name> names = participants.getNames();
        List<String> names2 = new ArrayList<>();
        for (Name name : names) {
            names2.add(name.getValue());
        }
        return String.format("딜러와 %s에게 2장을 나누었습니다.",String.join(", ", names2));
    }

    public String formatCardSet(Participant participant) {
        Name name = participant.getName();
        List<Card> cards = participant.getCards();
        List<String> cardNames = new ArrayList<>();
        for (Card card : cards) {
            cardNames.add(formatCard(card));
        }
        return String.format("%s카드: %s", name.getValue(), String.join(", ", cardNames));
    }

    public String formatCardResult(Participant participant) {
        return String.format("%s - 결과: %d", formatCardSet(participant), participant.getScore());
    }

    public String formatCard(Card card) {
        return card.getRank().getName() + card.getShape().getName();
    }

    public String formatResult(Map.Entry<Participant, Boolean> entry) {
        if (entry.getValue()) {
            return String.format("%s: 승", entry.getKey().getName().getValue());
        }
        return String.format("%s: 패", entry.getKey().getName().getValue());
    }

    public String formatDealerResult(Map<Participant, Boolean> result) {
        long dealerWinCount = result.values().stream()
                .filter(isWin -> !isWin)
                .count();

        int totalResult = result.keySet().size();
        return String.format("딜러: %d승 %d패", (int)dealerWinCount, totalResult - (int)dealerWinCount);
    }
}

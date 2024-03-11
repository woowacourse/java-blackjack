package view;

import domain.blackjack.BetAmount;
import domain.card.Card;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Participants;

import java.util.ArrayList;
import java.util.List;

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

    public String formatParticipantResult(Participant participant) {
        return String.format("%s - 결과: %d", formatHands(participant), participant.getScore());
    }

    public String formatDealerResult(BetAmount betAmount) {
        return String.format("딜러: %d", (int) betAmount.getDealerPayout());
    }

    public String formatBlackJackResult(Participant participant, double payout) {
        return String.format("%s: %d", participant.getName().getValue(), (int) payout);
    }
}

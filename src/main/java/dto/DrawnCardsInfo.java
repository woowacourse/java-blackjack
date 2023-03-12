package dto;

import domain.card.Card;
import domain.participants.Participant;
import java.util.ArrayList;
import java.util.List;

public class DrawnCardsInfo {

    private final String name;
    private final List<String> drawnCards;

    private DrawnCardsInfo(final String name, final List<String> drawnCards) {
        this.name = name;
        this.drawnCards = drawnCards;
    }

    public static DrawnCardsInfo toDto(final Participant participant) {
        List<String> cardInfos = new ArrayList<>();

        List<Card> cards = participant.openDrawnCards();

        for (Card drawnCard : cards) {
            cardInfos.add(drawnCard.getValue().getName() + drawnCard.getType().getName());
        }

        return new DrawnCardsInfo(participant.getName(), cardInfos);
    }

    public String getName() {
        return name;
    }

    public List<String> getDrawnCards() {
        return drawnCards;
    }
}

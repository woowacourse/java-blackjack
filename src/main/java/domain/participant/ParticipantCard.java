package domain.participant;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class ParticipantCard {

    private final List<Card> cards;

    private ParticipantCard() {
        this.cards = new ArrayList<>();
    }

    public static ParticipantCard create() {
        return new ParticipantCard();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    List<Card> getCards() {
        return List.copyOf(cards);
    }
}

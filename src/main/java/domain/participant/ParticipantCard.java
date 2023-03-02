package domain.participant;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class ParticipantCard {

    private static final int FIRST_CARD_INDEX = 0;

    private final List<Card> cards;

    private ParticipantCard() {
        this.cards = new ArrayList<>();
    }

    public static ParticipantCard create() {
        return new ParticipantCard();
    }

    void addCard(final Card card) {
        cards.add(card);
    }

    Card getFirstCard() {
        return cards.get(FIRST_CARD_INDEX);
    }

    List<Card> getCards() {
        return List.copyOf(cards);
    }
}

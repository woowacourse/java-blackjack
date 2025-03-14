package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import java.util.List;

abstract public class Participant {
    private final Hand cards;

    public Participant(Hand hand) {
        this.cards = hand;
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public int calculateSum() {
        return cards.calculateSum();
    }

    public abstract List<Card> openInitialCards();

    public Hand getCards() {
        return cards;
    }
}

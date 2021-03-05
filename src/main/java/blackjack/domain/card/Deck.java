package blackjack.domain.card;

import java.util.List;

public class Deck {

    private final CardStack cards;

    private Deck(CardStack cards) {
        this.cards = cards;
    }

    public static Deck create() {
        return new Deck(CardStack.create());
    }

    public List<Card> makeInitialHands() {
        return cards.getTwoCards();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card pick() {
        return cards.getSingleCard();
    }
}

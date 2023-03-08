package domain.card;

import java.util.List;
import java.util.Stack;

public class Deck {

    public final Stack<Card> cards;

    public Deck(final Stack<Card> cards) {
        this.cards = cards;
    }

    public Card drawCard() {
        return cards.pop();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}

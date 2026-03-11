package domain;

import domain.card.Card;
import domain.deck.Deck;
import java.util.List;

public class FixedDeck implements Deck {
    private final List<Card> deck;
    private int index;

    public FixedDeck(List<Card> deck) {
        this.deck = deck;
        this.index = 0;
    }

    public Card deal() {
        return deck.get(index++);
    }
}

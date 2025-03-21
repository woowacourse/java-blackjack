package card;

import java.util.*;

public final class CardDeck {

    private final List<Card> deck;

    public CardDeck(List<Card> deck) {
        this.deck = deck;
    }

    public Card pickCard() {
        return deck.removeFirst();
    }

    public List<Card> getDeck() {
        return deck;
    }
}

package domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class Deck {

    private final LinkedList<Card> cards;

    private Deck(LinkedList<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public static Deck initDeck(List<Card> cache) {
        LinkedList<Card> cards = new LinkedList<>(cache);
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    public List<Card> handOutInitialTwoCards() {
        return Arrays.asList(handOut(), handOut());
    }

    public Card handOut() {
        return cards.pop();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}

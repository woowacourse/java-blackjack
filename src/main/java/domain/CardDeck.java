package domain;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private final List<Card> cards;

    private CardDeck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static CardDeck generate() {
        return new CardDeck(Card.values());
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Card pick() {
        return cards.remove(0);
    }
}

package domain.deck;

import java.util.ArrayList;
import java.util.List;

public class DeckFactory {

    private static final Deck deck = createDeck();

    private static Deck createDeck() {
        List<Card> cards = new ArrayList<>();

        for (Symbol symbol : Symbol.values()) {
            cards.addAll(createCard(symbol));
        }

        return new Deck(cards);
    }

    private static List<Card> createCard(Symbol symbol) {
        List<Card> cards = new ArrayList<>();

        for (Type type : Type.values()) {
            cards.add(new Card(symbol, type));
        }

        return cards;
    }

    public static Deck getDeck() {
        return deck;
    }
}

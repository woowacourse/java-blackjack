package domain.deck;

import java.util.ArrayList;
import java.util.List;

public class DeckFactory {

    public static Deck createDeck() {
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
}

package domain.card;

import java.util.ArrayList;
import java.util.List;

public class DeckFactory {

    private static List<Card> DECK_CARDS = createDeckCards();

    private static List<Card> createDeckCards() {
        List<Card> cards = new ArrayList<>();

        for (Symbol symbol : Symbol.values()) {
            cards.addAll(createCard(symbol));
        }

        return cards;
    }

    private static List<Card> createCard(Symbol symbol) {
        List<Card> cards = new ArrayList<>();

        for (Type type : Type.values()) {
            cards.add(new Card(symbol, type));
        }

        return cards;
    }

    public static Deck createDeck() {
        return new Deck(DECK_CARDS);
    }
}

package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {
    public static Deck createDeck() {
        return new Deck(makeDeckCardList());
    }

    public static Deck createShuffledDeck() {
        List<Card> cards = makeDeckCardList();
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    private static List<Card> makeDeckCardList() {
        List<Card> cards = new ArrayList<>();
        for (Symbol symbol : Symbol.values()) {
            createByType(cards, symbol);
        }
        return cards;
    }

    private static void createByType(List<Card> cards, Symbol symbol) {
        for (Type type : Type.values()) {
            cards.add(new Card(symbol, type));
        }
    }
}

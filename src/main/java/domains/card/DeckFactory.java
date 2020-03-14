package domains.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DeckFactory {
    static List<Card> create() {
        List<Card> cards = new ArrayList<>();
        for (Symbol symbol : Symbol.values()) {
            createByType(cards, symbol);
        }
        Collections.shuffle(cards);
        return cards;
    }

    private static void createByType(List<Card> cards, Symbol symbol) {
        Type[] types = Type.values();
        for (Type type : types) {
            cards.add(new Card(symbol, type));
        }
    }
}
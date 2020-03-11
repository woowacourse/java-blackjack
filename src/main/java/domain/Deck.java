package domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final Cards cards;

    private Deck(Cards cards) {
        this.cards = cards;
    }

    public static Deck create() {
        List<Card> cards = new ArrayList<>();
        for(Type type : Type.values()) {
            cards.addAll(createBySymbol(type));
        }
        return new Deck(new Cards(cards));
    }

    private static List<Card> createBySymbol(Type type) {
        List<Card> cards = new ArrayList<>();
        for(Symbol symbol : Symbol.values()) {
            cards.add(new Card(symbol,type));
        }
        return cards;
    }
}

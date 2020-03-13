package model.card;

import java.util.*;

public class CardFactory {

    public static List<Card> createCardList() {
        List<Card> cards = new ArrayList<>();
        for (Symbol symbol : Symbol.values()) {
            createByType(cards, symbol);
        }
        return Collections.unmodifiableList(cards);
    }

    private static void createByType(List<Card> cards, Symbol symbol) {
        for (Type type : Type.values()) {
            cards.add(new Card(symbol, type));
        }
    }
}

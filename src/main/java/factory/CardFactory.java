package factory;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {
    private CardFactory() {
    }

    public static List<Card> create() {
        List<Card> cards = new ArrayList<>();

        for (Symbol symbol : Symbol.values()) {
            createByType(symbol, cards);
        }
        return Collections.unmodifiableList(cards);
    }

    private static void createByType(Symbol symbol, final List<Card> cards) {
        for (Type type : Type.values()) {
            cards.add(Card.of(type.getName(), symbol.getAlias()));
        }
    }
}

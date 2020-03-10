package carddeck;

import card.Card;
import card.CardFactory;
import card.Symbol;
import card.Type;

import java.util.LinkedList;
import java.util.List;

public class CardDeckFactory {
    private CardDeckFactory() {
        throw new AssertionError();
    }

    public static CardDeck create() {
        List<Card> cards = new LinkedList<>();
        Symbol[] symbols = Symbol.values();
        for (Symbol symbol : symbols) {
            createByType(cards, symbol);
        }
        return new CardDeck(cards);
    }

    private static void createByType(List<Card> cards, Symbol symbol) {
        Type[] types = Type.values();
        for (Type type : types) {
            cards.add(CardFactory.of(type, symbol));
        }
    }
}

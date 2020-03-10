package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {
    private static final List<Card> cards;

    static{
        cards = createCards();
    }

    private static List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        Symbol[] symbols = Symbol.values();
        for (Symbol symbol : symbols) {
            createByType(cards, symbol);
        }
        return Collections.unmodifiableList(cards);
    }

    private static void createByType(List<Card> cards, Symbol symbol) {
        Type[] types = Type.values();
        for (Type type : types) {
            cards.add(new Card(type, symbol));
        }
    }

    public static Card of(Type type, Symbol symbol) {
        for(Card card : cards) {
            if(card.equals(new Card(type, symbol))){
                return card;
            }
        }
        throw new IllegalArgumentException("값을 찾을 수 없습니다.");
    }
}

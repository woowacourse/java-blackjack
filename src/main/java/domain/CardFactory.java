package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {
    public static List<Card> create() {
        List<Card> cards = new ArrayList<>();
        for (Symbol symbol : Symbol.values()) {
            makeCardByShape(cards, symbol);
        }
        Collections.shuffle(cards);
        return Collections.unmodifiableList(cards);
    }

    private static void makeCardByShape(List<Card> cards, Symbol symbol) {
        for (Shape shape : Shape.values()) {
            cards.add(new Card(symbol, shape));
        }
    }

}

package BlackJack.domain.Card;

import java.util.*;

public class CardFactory {

    public static final LinkedList<Card> CARD_CACHE = new LinkedList<>();

    static {
        for (Shape shape : Shape.values()) {
            initCards(CARD_CACHE, shape);
        }
    }

    private static void initCards(LinkedList<Card> cards, Shape shape) {
        for (Number number : Number.values()) {
            cards.add(new Card(shape, number));
        }
    }
}

package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final List<Card> cards = new ArrayList<>();

    static {
        for (CardShape cardShape : CardShape.values()) {
            matching(cardShape);
        }
    }

    private Deck() {
    }

    public static void shuffle() {
        Collections.shuffle(cards);
    }

    public static Card draw() {
        return cards.removeFirst();
    }

    private static void matching(CardShape cardShape) {
        for (CardValue cardValue : CardValue.values()) {
            cards.add(new Card(cardValue, cardShape));
        }
    }

}

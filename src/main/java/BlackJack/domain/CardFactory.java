package BlackJack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardFactory {

    static List<Card> CARD_CACHE = new ArrayList<>();

    static {
        for (Shape shape : Shape.values()) {
            for (Number number : Number.values()) {
                CARD_CACHE.add(new Card(shape, number));
            }
        }
        Collections.shuffle(CARD_CACHE);

    }

    public static List<Card> drawTwoCards() {
        List<Card> cards = CARD_CACHE.subList(0, 2);
        CARD_CACHE = new ArrayList<>(CARD_CACHE.subList(2, CARD_CACHE.size()));
        return cards;
    }

    public static Card drawOneCard() {
        Card card = CARD_CACHE.get(0);
        CARD_CACHE = new ArrayList<>(CARD_CACHE.subList(1, CARD_CACHE.size()));
        return card;
    }

}

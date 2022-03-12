package BlackJack.domain.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardFactory {

    static List<Card> CARD_CACHE = new ArrayList<>();

    static {
        Arrays.stream(Shape.values())
                .forEach(shape -> Arrays.stream(Number.values())
                        .forEach(number -> CARD_CACHE.add(new Card(shape, number))));
        Collections.shuffle(CARD_CACHE);

    }

    public static Cards drawTwoCards() {
        List<Card> cards = CARD_CACHE.subList(0, 2);
        CARD_CACHE = new ArrayList<>(CARD_CACHE.subList(2, CARD_CACHE.size()));
        return new Cards(cards);
    }

    public static Card drawOneCard() {
        Card card = CARD_CACHE.get(0);
        CARD_CACHE = new ArrayList<>(CARD_CACHE.subList(1, CARD_CACHE.size()));
        return card;
    }

}

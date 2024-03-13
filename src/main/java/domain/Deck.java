package domain;

import domain.constants.Score;
import domain.constants.Shape;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Deck {
    private static final Map<Integer, Card> CARD_CACHE = new HashMap<>();

    static {
        List<Card> cards = Arrays.stream(Shape.values())
                .flatMap(shape -> Arrays.stream(Score.values()).map(score -> new Card(score, shape)))
                .toList();
        for (int i = 0; i < cards.size(); i++) {
            CARD_CACHE.put(i, cards.get(i));
        }
    }

    private Deck() {
    }

    public static Card pick(final int index) {
        return CARD_CACHE.get(index);
    }

    public static int getSize() {
        return CARD_CACHE.size();
    }
}

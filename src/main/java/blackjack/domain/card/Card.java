package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;

public final class Card {

    private static final Map<String, Card> cache = new HashMap<>();
    private final CardNumber number;
    private final CardShape shape;


    private Card(final CardNumber number, final CardShape shape) {
        this.number = number;
        this.shape = shape;
    }

    public static Card of(final CardNumber number, final CardShape shape) {
        final String cardKey = number.getName() + shape.getName();

        if (!cache.containsKey(cardKey)) {
            cache.put(cardKey, new Card(number, shape));
        }

        return cache.get(cardKey);
    }

    public CardNumber getNumber() {
        return number;
    }

    public CardShape getShape() {
        return shape;
    }
}

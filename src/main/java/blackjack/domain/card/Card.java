package blackjack.domain.card;

import java.util.LinkedHashMap;
import java.util.Map;

public record Card(CardNumber number, CardShape shape) {

    private static class CardCache {

        static final Map<String, Card> cache;

        static {
            cache = new LinkedHashMap<>();

            CardShape.stream().forEach(shape -> CardNumber.stream()
                    .forEach(number -> cache.put(toKey(number, shape), new Card(number, shape))));
        }

        private static String toKey(final CardNumber number, final CardShape shape) {
            return number.name() + shape.name();
        }

        private CardCache() {
        }
    }

    public static Card of(final CardNumber number, final CardShape shape) {
        return CardCache.cache.get(CardCache.toKey(number, shape));
    }

    public boolean isAce() {
        return number == CardNumber.ACE;
    }

    public int getNumber() {
        return number.getNumber();
    }

    public String getNumberName() {
        return number.name();
    }

    public String getShapeName() {
        return shape.name();
    }

    @Override
    public String toString() {
        return number.name() + "_" + shape.name();
    }
}

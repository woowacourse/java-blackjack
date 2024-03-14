package blackjack.domain.card;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.function.Function;

public record Card(CardNumber number, CardShape shape) {

    private static class CardCache {

        static final Map<String, Card> cache;

        static {
            cache = CardShape.stream()
                    .flatMap(shape -> CardNumber.stream().map(number -> new Card(number, shape)))
                    .collect(toMap(card -> toKey(card.number(), card.shape()), Function.identity()));
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

    @Override
    public String toString() {
        return number.name() + "_" + shape.name();
    }
}

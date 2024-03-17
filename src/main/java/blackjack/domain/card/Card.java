package blackjack.domain.card;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.rule.Score;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public record Card(CardNumber number, CardShape shape) {

    private static class CardCache {

        static final Map<String, Card> cache;

        static {
            cache = Arrays.stream(CardShape.values())
                    .flatMap(shape -> Arrays.stream(CardNumber.values()).map(number -> new Card(number, shape)))
                    .collect(toMap(card -> toKey(card.number(), card.shape()), Function.identity()));
        }

        private static String toKey(CardNumber number, CardShape shape) {
            return number.name() + shape.name();
        }

        private CardCache() {
        }
    }

    public static Card of(CardNumber number, CardShape shape) {
        return CardCache.cache.get(CardCache.toKey(number, shape));
    }

    public boolean isAce() {
        return number == CardNumber.ACE;
    }

    public Score getScore() {
        return number.getScore();
    }

    @Override
    public String toString() {
        return number.name() + "_" + shape.name();
    }
}

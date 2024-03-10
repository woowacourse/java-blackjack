package blackjack.domain.card;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public record Card(CardNumber number, CardShape shape) {

    private static class CardCache {

        static final Map<CardShape, Map<CardNumber, Card>> cache;
        static Map<CardShape, Map<CardNumber, Card>> archivedCache;

        static {
            archivedCache = new EnumMap<>(CardShape.class);

            final List<CardNumber> numbers = Arrays.asList(CardNumber.values());

            Arrays.stream(CardShape.values()).forEach(shape -> archivedCache.put(shape, numbers.stream().collect(
                    toMap(Function.identity(), number -> new Card(number, shape), (v1, v2) -> v1,
                            () -> new EnumMap<>(CardNumber.class)))));

            cache = archivedCache;
        }

        private CardCache() {
        }
    }

    public static Card valueOf(final CardNumber number, final CardShape shape) {
        return CardCache.cache.get(shape).get(number);
    }

    public boolean isAce() {
        return number.isAce();
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
}

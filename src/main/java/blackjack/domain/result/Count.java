package blackjack.domain.result;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Count(int value) {
    private static final int CACHE_MIN = 0;
    private static final int CACHE_MAX = 5;
    private static final Map<Integer, Count> COUNT_CACHE = initializeCache();

    private static Map<Integer, Count> initializeCache() {
        return IntStream.range(CACHE_MIN, CACHE_MAX)
                        .boxed()
                        .collect(Collectors.toMap(Function.identity(), Count::new));
    }

    public static Count valueOf(final int value) {
        if (COUNT_CACHE.containsKey(value)) {
            return COUNT_CACHE.get(value);
        }
        return new Count(value);
    }

    public static Count initialValue() {
        return Count.valueOf(0);
    }

    public Count increment() {
        return Count.valueOf(value + 1);
    }

    public int toInt() {
        return this.value;
    }
}

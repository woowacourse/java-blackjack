package blackjack.utils;

import blackjack.domain.Pair;
import java.util.List;
import java.util.stream.Stream;

public class Lists {

    public static <T, U> List<Pair<T, U>> cartesianProduct(final List<T> list1,
        final List<U> list2) {
        return list1.stream()
            .flatMap(t -> pairWithAll(t, list2))
            .toList();
    }

    private static <T, U> Stream<Pair<T, U>> pairWithAll(final T t, final List<U> list) {
        return list.stream()
            .map(u -> new Pair<>(t, u));
    }

}

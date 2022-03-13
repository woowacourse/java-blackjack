package model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiPredicate;

public enum Status {
    BUST((cardCount, cardSum) -> cardCount >= 2 && cardSum > 21),
    STAND((cardCount, cardSum) -> (cardCount > 2 && cardSum <= 21) || (cardCount == 2 && cardSum < 21)),
    BLACKJACK((cardCount, cardSum) -> cardCount == 2 && cardSum == 21);

    private final BiPredicate<Integer, Integer> predicate;

    Status(BiPredicate<Integer, Integer> predicate) {
        this.predicate = predicate;
    }

    public static Status of(int cardCount, int cardSum) {
        return Arrays.stream(Status.values())
                .filter(status -> status.predicate.test(cardCount, cardSum))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

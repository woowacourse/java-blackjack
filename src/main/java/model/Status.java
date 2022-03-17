package model;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Status {
    BUST((cardCount, cardSum) -> cardCount >= 2 && cardSum > 21, 1),
    STAND((cardCount, cardSum) -> (cardCount > 2 && cardSum <= 21) || (cardCount == 2 && cardSum < 21), 1),
    BLACKJACK((cardCount, cardSum) -> cardCount == 2 && cardSum == 21, 1.5);

    private final BiPredicate<Integer, Integer> predicate;
    private final double winMargin;

    Status(BiPredicate<Integer, Integer> predicate, double winMargin) {
        this.predicate = predicate;
        this.winMargin = winMargin;
    }

    public static Status of(int cardCount, int cardSum) {
        return Arrays.stream(Status.values())
                .filter(status -> status.predicate.test(cardCount, cardSum))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public long calculateWinMargin(long bettingAmount) {
        return (long) (this.winMargin * bettingAmount);
    }
}

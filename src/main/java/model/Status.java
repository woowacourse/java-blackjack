package model;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Status {
    BLACKJACK(3, (cardCount, cardSum) -> cardCount == 2 && cardSum == 21),
    STAND(2, (cardCount, cardSum) -> (cardCount > 2 && cardSum <= 21) || (cardCount == 2 && cardSum < 21)),
    BUST(1, (cardCount, cardSum) -> cardSum > 21);

    private final BiPredicate<Integer, Integer> predicate;
    private final int power;

    Status(int power, BiPredicate<Integer, Integer> predicate) {
        this.power = power;
        this.predicate = predicate;
    }

    public static Status of(int cardCount, int cardSum) {
        return Arrays.stream(Status.values())
                .filter(status -> status.predicate.test(cardCount, cardSum))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static Result getResultOf(Status from, Status to) {
        return Result.of(from.power, to.power);
    }

    public boolean isStand() {
        return this.equals(STAND);
    }

    public boolean isBusted() {
        return this.equals(BUST);
    }
}

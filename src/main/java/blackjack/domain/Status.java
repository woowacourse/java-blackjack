package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Status {
    BLACKJACK((count, sum) -> count == 2 && sum == 21),
    BUST((count, sum) -> sum > 21),
    NONE((count, sum) -> sum < 21);

    private final BiPredicate<Integer, Integer> status;

    Status(BiPredicate<Integer, Integer> status) {
        this.status = status;
    }

    public static Status of(int count, int sum) {
        return Arrays.stream(Status.values())
                .filter(status -> status.status.test(count, sum))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 값 입니다."));
    }

    public boolean isBlackjack() {
        return this == BLACKJACK;
    }

    public boolean isBust() {
        return this == BUST;
    }

    public boolean isNone() {
        return this == NONE;
    }
}

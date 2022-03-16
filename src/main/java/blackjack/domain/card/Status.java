package blackjack.domain.card;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Status {
    BLACKJACK((count, sum) -> count == 2 && sum == 21),
    BUST((count, sum) -> sum > 21),
    NONE((count, sum) -> sum < 21
            || (sum == 21 && count != 2));

    private final BiPredicate<Integer, Integer> condition;

    Status(BiPredicate<Integer, Integer> condition) {
        this.condition = condition;
    }

    public static Status findStatus(int count, int sum) {
        return Arrays.stream(Status.values())
                .filter(status -> status.condition.test(count, sum))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 cards 입니다."));
    }
}

package blackjack.domain.game;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Status {
    BLACKJACK((count, sum) -> count == GameResult.BLACKJACK_COUNT && sum == GameResult.BLACKJACK_VALUE),
    BUST((count, sum) -> sum > GameResult.BLACKJACK_VALUE),
    NONE((count, sum) -> sum < GameResult.BLACKJACK_VALUE
            || (sum == GameResult.BLACKJACK_VALUE && count != GameResult.BLACKJACK_COUNT));

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

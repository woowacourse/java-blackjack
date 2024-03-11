package domain.game;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    WIN((current, opponent) -> current > opponent),
    LOSE((current, opponent) -> current < opponent),
    DRAW(Integer::equals);

    private final BiPredicate<Integer, Integer> condition;

    Result(BiPredicate<Integer, Integer> condition) {
        this.condition = condition;
    }

    public Result reverse() {
        return switch (this) {
            case WIN -> LOSE;
            case LOSE -> WIN;
            case DRAW -> DRAW;
        };
    }

    public static Result compare(int current, int opponent) {
        return Arrays.stream(values())
                .filter(result -> result.condition.test(current, opponent))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("Result의 BiPredicate가 모든 경우의 수를 포함하지 않습니다."));
    }
}

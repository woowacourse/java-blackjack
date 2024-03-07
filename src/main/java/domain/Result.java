package domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    WIN("승", (current, opponent) -> current > opponent),
    LOSE("패", (current, opponent) -> current < opponent),
    DRAW("무", Integer::equals);

    private final String result;
    private final BiPredicate<Integer, Integer> condition;

    Result(String result, BiPredicate<Integer, Integer> condition) {
        this.result = result;
        this.condition = condition;
    }

    public Result reverse() {
        return switch (this) {
            case WIN -> LOSE;
            case LOSE -> WIN;
            case DRAW -> DRAW;
        };
    }

    public String getResult() {
        return result;
    }

    public static Result compare(int current, int opponent) {
        return Arrays.stream(values())
                .filter(result -> result.condition.test(current, opponent))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Result의 BiPredicate가 모든 경우의 수를 포함하지 않습니다."));
    }
}

package model;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    WIN((from, to) -> from > to),
    LOSE((from, to) -> from < to),
    DRAW((from, to) -> from.equals(to));

    private final BiPredicate<Integer, Integer> resultCriteria;

    Result(BiPredicate<Integer, Integer> resultCriteria) {
        this.resultCriteria = resultCriteria;
    }

    public static Result of(int from, int to) {
        return Arrays.stream(Result.values())
                .filter(result -> result.resultCriteria.test(from, to))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static Result of(Status from, Status to) {
        return Status.getResultOf(from, to);
    }

    public Result getOpposite() {
        if (this.equals(WIN)) {
            return LOSE;
        }
        if (this.equals(LOSE)) {
            return WIN;
        }
        return this;
    }
}

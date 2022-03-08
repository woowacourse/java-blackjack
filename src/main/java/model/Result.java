package model;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Result {
    WIN((from, to) -> (from <= 21 && to > 21) || (from <= 21 && from > to)),
    LOSE((from, to) -> (from > 21 && to <= 21) || (to <= 21 && from < to)),
    DRAW((from, to) -> (from > 21 && to > 21) || (from == to));

    private final BiFunction<Integer, Integer, Boolean> resultCriteria;

    Result(BiFunction<Integer, Integer, Boolean> resultCriteria) {
        this.resultCriteria = resultCriteria;
    }

    public static Result of(int from, int to) {
        return Arrays.stream(Result.values())
                .filter(result -> result.resultCriteria.apply(from, to))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

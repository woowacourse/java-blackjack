package blackjack.domain;

import java.util.Arrays;
import java.util.function.Function;

public enum Result {
    WIN((difference) -> difference > 0, "승"),
    LOSE((difference) -> difference < 0, "패"),
    TIE((difference) -> difference == 0, "무");

    private final Function<Integer, Boolean> matcher;
    private final String name;

    Result(Function<Integer, Boolean> matcher, String name) {
        this.matcher = matcher;
        this.name = name;
    }

    public static Result getResult(int difference) {
        return Arrays.stream(Result.values())
            .filter(result -> result.matcher.apply(difference))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("불가능한 결과입니다."));
    }
}

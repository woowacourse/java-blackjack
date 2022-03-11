package blackjack.domain;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public enum Result {
    WIN(number -> number > 0),
    DRAW(number -> number == 0),
    LOSE(number -> number < 0);

    private Function<Integer, Boolean> expression;

    Result(Function<Integer, Boolean> expression) {
        this.expression = expression;
    }

    public static Result valueOf(int number) {
        return Stream.of(values())
                .filter(result -> result.expression.apply(number))
                .findFirst()
                .orElseThrow();
    }
}

package blackjack.domain;

import java.util.function.Function;
import java.util.stream.Stream;

public enum Result {
    WIN("승", number -> number < 0),
    DRAW("무", number -> number == 0),
    LOSE("패", number -> number > 0);

    private final String name;
    private final Function<Integer, Boolean> expression;

    Result(String name, Function<Integer, Boolean> expression) {
        this.name = name;
        this.expression = expression;
    }

    public static Result valueOf(int number) {
        return Stream.of(values())
                .filter(result -> result.expression.apply(number))
                .findFirst()
                .orElseThrow();
    }

    public String getName() {
        return name;
    }
}

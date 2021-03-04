package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public enum Outcome {
    WIN("승",result -> result == 1),
    LOSE("패",result -> result == -1),
    DRAW("무",result -> result == 0);

    private final String word;
    private final Predicate<Integer> expression;

    Outcome(String word, Predicate<Integer> expression) {
        this.word = word;
        this.expression = expression;
    }

    public static Outcome getInstance(int result) {
        return Arrays.stream(Outcome.values())
                .filter(outcome -> outcome.expression.test(result))
                .findFirst().orElse(null);
    }

    public String getWord() {
        return word;
    }
}

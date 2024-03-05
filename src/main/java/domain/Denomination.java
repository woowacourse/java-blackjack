package domain;

import java.util.Arrays;
import java.util.function.Function;

public enum Denomination {
    ACE(determineAceValue()),
    TWO(number -> 2),
    THREE(number -> 3),
    FOUR(number -> 4),
    FIVE(number -> 5),
    SIX(number -> 6),
    SEVEN(number -> 7),
    EIGHT(number -> 8),
    NINE(number -> 9),
    TEN(number -> 10),
    JACK(number -> 10),
    QUEEN(number -> 10),
    KING(number -> 10);

    private static Function<Integer, Integer> determineAceValue() {
        return number -> {
            if (number + 11 <= 21) {
                return 11;
            }
            return 1;
        };
    }

    private final Function<Integer, Integer> function;

    Denomination(final Function<Integer, Integer> function) {
        this.function = function;
    }

    public int apply(final int score) {
        return this.function.apply(score);
    }

    public static Denomination getDenomination(int number) {
        return Arrays.stream(Denomination.values())
                .filter(denomination -> denomination.ordinal() == number)
                .findAny()
                .orElseThrow();
    }
}
